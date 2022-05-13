package com.demo.influxdb;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-04-30 22:33
 *
 * @author 汪强
 */
public class TestLengthFieldDecoder {
    public static void main(String[] args) {
        //testTimeout();
        test4();
    }

    private static void test1() {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4),
                new LoggingHandler(LogLevel.DEBUG)
        );

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

        send(byteBuf, "hello world");
        send(byteBuf, "welcome to Beijing");
        channel.writeInbound(byteBuf);

    }

    private static void send(ByteBuf buffer, String content) {
        byte[] bytes = content.getBytes();
        int length = bytes.length;
        buffer.writeInt(length);
        buffer.writeBytes(bytes);
    }

    private static void testTimeout() {

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler());
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8080);
            future.sync().channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    private static void test4() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888, 2);
            Socket socket = serverSocket.accept();
            System.out.println(socket);
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }

        //client
        Socket s=new Socket();
        //s.connect(new InetSocketAddress("127.0.0.1",8888),1000);
    }
}
