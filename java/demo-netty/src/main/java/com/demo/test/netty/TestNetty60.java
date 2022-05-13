package com.demo.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-03 11:41
 *
 * @author
 */
public class TestNetty60 {
    private final static Logger logger = LoggerFactory.getLogger(TestNetty60.class);

    public static void main(String[] args) {
        //任务处理线程组
        EventLoopGroup group = new DefaultEventLoop();
        new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(group, "group", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                ByteBufUtils.log(buf);
                                logger.info(buf.toString());
                            }
                        });
                    }
                }).bind(8080);
    }
}
