package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * 说明:
 * <p> LengthFieldBasedFrameDecoder 拆包
 * <br>
 * 创建时间：2022-05-02 21:55
 *
 * @author 
 */
public class TestNetty98 {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4),
                new LoggingHandler()
        );

        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        send(buf, "hello,world");
        send(buf, "Hi!");
        channel.writeInbound(buf);
    }

    private static void send(ByteBuf buf, String content) {
        byte[] bytes = content.getBytes();
        int length = bytes.length;
        buf.writeInt(length);
        buf.writeBytes(bytes);
    }
}
