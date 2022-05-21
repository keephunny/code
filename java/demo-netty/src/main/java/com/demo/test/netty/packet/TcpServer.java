package com.demo.test.netty.packet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-21 10:40
 *
 * @author
 */
public class TcpServer {
    private final Logger logger = LoggerFactory.getLogger(TcpServer.class);
    //接受数据缓冲大小
    private final static int SO_RCVBUF = 1024 * 2;
    private final static int PORT = 8080;

    void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .option(ChannelOption.SO_RCVBUF, SO_RCVBUF)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            logger.info("建立连接:{}", ch);
                            //ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringPacketHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(PORT);
            channelFuture.sync();
            //关闭channel
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("", e);

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TcpServer().start();
    }
}
