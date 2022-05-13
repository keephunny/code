package com.demo.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-03 15:54
 *
 * @author
 */
public class TestNetty65 {
    public static void main(String[] args) throws Exception {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                //异步非阻塞
                .connect(new InetSocketAddress("127.0.0.1", 8080));
        //方法一 阻塞
        channelFuture.sync();
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("hello world");

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("q".equals(line)) {
                    channel.close();
                    break;
                }
                channel.writeAndFlush(line);
            }
        });
//        //方法二 回调
//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                Channel channel = future.channel();
//                channel.writeAndFlush("hello world2");
//            }
//        });
    }
}
