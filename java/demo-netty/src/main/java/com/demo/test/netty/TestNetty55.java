package com.demo.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-15 17:21
 *
 * @author
 */
public class TestNetty55 {
    public static void main(String[] args) throws Exception {
//启动类
new Bootstrap()
    //添加EventLoop
    .group(new NioEventLoopGroup())
    //客户端channel实现
    .channel(NioSocketChannel.class)
    //添加处理器
    .handler(new ChannelInitializer<NioSocketChannel>() {
        @Override
        protected void initChannel(NioSocketChannel ch) throws Exception {
            ch.pipeline().addLast(new StringEncoder());
        }
    })
    .connect(new InetSocketAddress("127.0.0.1", 8080))
    .sync()
    .channel()
    .writeAndFlush("xxxxx");
    }
}
