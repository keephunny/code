package com.demo.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明: netty-server
 * <p>
 * <br>
 * 创建时间：2022-05-15 15:15
 *
 * @author
 */
public class TestNetty54 {
    private final static Logger logger = LoggerFactory.getLogger(TestNetty54.class);

    public static void main(String[] args) {
//启动器
        new ServerBootstrap()
                //BossEventLoop workerEventLoop(selector,thread) group组
                .group(new NioEventLoopGroup())
                //服务器端 NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //boss负责处理连接   worker负责处理读写
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //客户端数据读写通道，添加handler
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //添田口口String解码器
                        ch.pipeline().addLast(new StringEncoder());
                        //添加读handler
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                logger.info("接收内容:{}", msg);
                            }
                        });
                    }
                })
                //绑定端口
                .bind(8080);
    }
}
