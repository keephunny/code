package com.demo.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-15 07:52
 *
 * @author
 */
public class TestNetty75 {
    private final static Logger logger = LoggerFactory.getLogger(TestNetty75.class);

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        //1.通过channel拿到pipeline
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                logger.info("in1");
                                ByteBuf buf = (ByteBuf) msg;
                                String name = buf.toString(Charset.defaultCharset());

                                //super 将传递下一个handler
                                super.channelRead(ctx, name);
                            }
                        });
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                logger.info("in2");
                                channel.writeAndFlush(0xff);
                                ctx.writeAndFlush(0xff);

                                String name = (String) msg;
                                logger.info("上一个name的传值 {}", name);

                                //super 将传递下一个handler
                                //如果是最后一个 则不需要调用
                                super.channelRead(ctx, msg);
                            }
                        });

                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                logger.info("out3");
                                super.write(ctx, msg, promise);
                            }
                        });

                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                logger.info("out4");
                                super.write(ctx, msg, promise);
                            }
                        });

                        //如果不回写 将不会触发OutboundHandler

                    }
                })
                .bind(8080);
    }
}
