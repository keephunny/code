package com.demo.test.netty.frame;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 说明: 长度字段解码器
 * <p>
 * <br>
 * 创建时间：2022-05-18 16:01
 *
 * @author
 */
public class TcpServerLengthBase {
    private final Logger logger = LoggerFactory.getLogger(TcpServerLengthBase.class);

    void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .option(ChannelOption.SO_BACKLOG, 1024 * 2) //设置TCP缓冲区
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) //设置接受数据缓冲大小
                    //.childOption(ChannelOption.SO_KEEPALIVE, true)//保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            logger.info("收到新的客户端连接: {}",ch.toString());
                            ch.pipeline().addLast(new TcpServerLengthBaseHandler());
                            //ch.pipeline().addLast(new StringDecoder());
                            //ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//                                @Override
//                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                    ByteBuf buf = (ByteBuf) msg;
//                                    logger.info("接收数据 size:{}", buf.readableBytes());
//                                    logger.info("接收数据:{}", ByteBufUtil.hexDump(buf));
//                                }
//                            });
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
        new TcpServerLengthBase().start();
    }
}
