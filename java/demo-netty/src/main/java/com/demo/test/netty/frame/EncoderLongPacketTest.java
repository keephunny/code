package com.demo.test.netty.frame;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 说明: 长报文粘包
 * <p>
 * <br>
 * 创建时间：2022-05-18 21:31
 *
 * @author
 */
public class EncoderLongPacketTest {
    private final static Logger logger = LoggerFactory.getLogger(EncoderLongPacketTest.class);

    //数据最大长度
    private final static int maxFrameLength = 1014 * 1024;
    //数据长度标识的起始偏移量
    private final static int lengthFieldOffset = 2;
    //数据长度标识所占字节数
    private final static int lengthFieldLength = 4;
    //长度表示与有用数据的偏移量
    private final static int lengthAdjustment = 1;
    private final static int initialBytesToStrip = 7;

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip),
                new LoggingHandler(LogLevel.DEBUG)
        );


        // 模拟客户端，写入数据
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append("0123456789abcdef");
        }
        send(buffer, sb.toString());
        channel.writeInbound(buffer);

    }

    private static void send(ByteBuf buf, String msg) {
        // 得到数据的长度
        int length = msg.length();
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        // 将数据信息写入buf
        // 写入长度标识前的其他信息
        buf.writeByte(0xF0);
        buf.writeByte(0xF0);

        // 写入数据长度标识
        buf.writeInt(length);
        // 写入长度标识后的其他信息
        buf.writeByte(0xFE);
        // 写入具体的数据
        buf.writeBytes(bytes);
    }
}
