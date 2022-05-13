package com.demo.test.netty102;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-01 18:35
 *
 * @author
 */
public class MessageCodec extends ByteToMessageCodec<Message> {
    /**
     * 编码
     *
     * @param ctx
     * @param message
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        System.out.println("encode");
        //帧头
        out.writeBytes(new byte[]{1, 2, 3, 4});
        //序列化方式
        out.writeByte(1);

        //将对象转为字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(message);
        byte[] bytes = bos.toByteArray();

        //长度
        out.writeInt(bytes.length);
        //内容
        out.writeBytes(bytes);
        System.out.println("长度:"+bytes.length);
        //ctx.writeAndFlush(out);
    }

    /**
     * 解码
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode");
        //解码与编码反向
        //读取帧头
        int frameFlag = in.readInt();
        //序列化方式
        int ver = in.readByte();
        //长度
        int len = in.readInt();

        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        System.out.println(message);

        out.add(message);
    }
}
