package com.demo.influxdb;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * 说明:
 * 模拟redis连接协议，向redis发送指令
 * <br>
 * 创建时间：2022-05-01 15:36
 *
 * @author 汪强
 */
public class TestNetty99 {
    public static void main(String[] args) {
        //回车换行符
        final byte[] LINE = {13, 10};
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new LoggingHandler());
                    //自定义编解码器
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            //获取ByteBuf
                            ByteBuf buf = ctx.alloc().buffer();
                            buf.writeBytes("*3".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("$3".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("set".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("$5".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("key1".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("$6".getBytes());
                            buf.writeBytes(LINE);
                            buf.writeBytes("value1".getBytes());
                            buf.writeBytes(LINE);
                            ctx.writeAndFlush(buf);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf but = (ByteBuf) msg;

                            //成功返回  +ok
                            System.out.println(but.toString(Charset.defaultCharset()));
                        }
                    });
                }
            });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6379).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }
    }
}
