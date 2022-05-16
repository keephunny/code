package com.demo.test.netty;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.nio.charset.StandardCharsets;

/**
 * 说明: EmbeddedChhannel 测试类
 * <p>
 * <br>
 * 创建时间：2022-05-15 07:34
 *
 * @author
 */
public class TestNetty78 {
    private final static Logger logger = LoggerFactory.getLogger(TestNetty78.class);

    public static void main(String[] args) {
        ChannelInboundHandlerAdapter h1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                logger.info("channel1");
                super.channelRead(ctx, msg);
            }
        };

        ChannelInboundHandlerAdapter h2 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                logger.info("channel2");
                try {
                    System.out.println(1 / 0);
                }catch (Exception e){

                }
                super.channelRead(ctx, msg);
            }
        };

        ChannelOutboundHandlerAdapter h3 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                logger.info("channel3");
                super.write(ctx, msg, promise);
            }
        };

        ChannelOutboundHandlerAdapter h4 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                logger.info("channel4");
                super.write(ctx, msg, promise);
            }
        };

        //测试handler的channel
        EmbeddedChannel channel = new EmbeddedChannel(h1, h2, h3, h4);
        //执行inbound
        channel.writeInbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("inbound".getBytes(StandardCharsets.UTF_8)));
        //执行outbound
        channel.writeOutbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("outboud".getBytes(StandardCharsets.UTF_8)));

    }
}
