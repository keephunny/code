package com.demo.test.netty.frame;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明: 超长接收包大小 ，避免拆包
 * <p>
 * <br>
 * 创建时间：2022-05-18 16:01
 *
 * @author
 */
public class TcpServerLongRec {
    private final Logger logger = LoggerFactory.getLogger(TcpServerLongRec.class);

    void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .option(ChannelOption.SO_BACKLOG, 1024 * 2) //设置TCP缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024) //设置发送数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) //设置接受数据缓冲大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //最大接收分包长度  默认是1024 超过则分包
                            ch.config().setRecvByteBufAllocator(new FixedRecvByteBufAllocator(1024 * 10));
                            //ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    //建立连接时
                                    logger.info("接收:{}", ctx.channel());
                                    super.channelActive(ctx);
                                    logger.info("super.channelActive()");
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    logger.info("接收:{}", ctx.channel());
                                    super.channelActive(ctx);
                                    logger.info("super.channelInactive()");
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = (ByteBuf) msg;
                                    logger.info("接收数据 size:{}", buf.readableBytes());
                                    logger.info("接收数据:{}", ByteBufUtil.hexDump(buf));
                                }
                            });
                        }
                    });


            ChannelFuture channelFuture = serverBootstrap.bind(8080);
            logger.debug("{} binding...", channelFuture.channel());
            channelFuture.sync();
            logger.debug("{} bound...", channelFuture.channel());
            // 关闭channel
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TcpServerLongRec().start();
    }
}
