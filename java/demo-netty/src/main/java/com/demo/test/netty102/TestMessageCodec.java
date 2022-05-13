package com.demo.test.netty102;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-01 18:48
 *
 * @author
 */
public class TestMessageCodec {
    private static final Logger logger = LoggerFactory.getLogger(TestMessageCodec.class);

    public static void main(String[] args) throws Exception {
        logger.info("开始");
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024 * 1, 5, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG),
                new MessageCodec()
        );

        //encode
        Message msg = new Message("1", "aaaa");
        channel.writeOutbound(msg);

        //decode生成一个byteBuf
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, msg, buf);

        //入
        channel.writeInbound(buf);

    }
}
