package com.demo.test.netty.frame;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-18 18:25
 *
 * @author
 */
public class TcpServerLengthBaseHandler extends LengthFieldBasedFrameDecoder {
    private final Logger logger = LoggerFactory.getLogger(TcpServerLengthBaseHandler.class);
    //数据最大长度
    private final static int maxFrameLength = 1014 * 1024;
    //数据长度标识的起始偏移量
    private final static int lengthFieldOffset = 2;
    //数据长度标识所占字节数
    private final static int lengthFieldLength = 3;
    //长度表示与有用数据的偏移量
    private final static int lengthAdjustment = 0;
    //数据读取起点 跳过多少位读取
    private final static int initialBytesToStrip =0;// lengthFieldOffset + lengthFieldLength;

    public TcpServerLengthBaseHandler() {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //ByteBuf buf = (ByteBuf) super.decode(ctx, in);
        logger.info("{}", in.readableBytes());
        logger.info("{}", ByteBufUtil.hexDump(in));

        //后面没有handler了
        //return buf;
        return null;
    }



}
