package com.demo.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 说明:
 * <p> http协议
 * <br>
 * 创建时间：2022-05-01 15:54
 *
 * @author
 */
public class TestNetty100 {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    //http编解码器
                    ch.pipeline().addLast(new HttpServerCodec());
                    //自定义编解码器
//                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//                        @Override
//                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                            //查看msg对象类型
//                            System.out.println(msg.getClass());
//                            //请求头
//                            if (msg instanceof HttpRequest) {
//                                System.out.println("head");
//                            }
//                            //请求体
//                            if (msg instanceof HttpContent) {
//                                System.out.println("body");
//                            }
//                        }
//                    });
                    //只处理httpRequest请求解码器
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest httpRequest) throws Exception {
                            System.out.println("地址:" + httpRequest.getUri());
                            //响应返回
                            DefaultFullHttpResponse response = new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.OK);
                            byte[] bytes="ok".getBytes();
                            //指定消息长度，不然浏览器一直等待获取 content-length
                            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH,bytes.length);
                            response.content().writeBytes(bytes);
                            ctx.writeAndFlush(response);
                        }
                    });
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
