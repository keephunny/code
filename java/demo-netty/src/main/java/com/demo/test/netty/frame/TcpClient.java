package com.demo.test.netty.frame;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-18 16:18
 *
 * @author
 */
public class TcpClient {
    private final static Logger logger = LoggerFactory.getLogger(TcpClient.class);

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class)
                    .group(worker)
                    .option(ChannelOption.SO_SNDBUF, 1024 * 32) //设置发送数据缓冲大小
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    logger.info("send");
                                    ByteBuf buf = ctx.alloc().buffer();
                                    //头标识 F0 F0
                                    buf.writeBytes(new byte[]{120, 120});
                                    int dataLen=6;
                                    int total =  dataLen * 16 +10 ;
                                    buf.writeMedium(total);
                                    for (int i = 0; i < dataLen; i++) {
                                        buf.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                                    }
                                    ctx.writeAndFlush(buf);
                                }

                            });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            logger.error("", e);

        } finally {
            worker.shutdownGracefully();
        }
    }
}
