package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ByteBuf buf= ByteBufAllocator.DEFAULT.buffer(16,20);
        buf.writeBytes(new byte[]{1,2,3,4});
        buf.writeInt(5);

        System.out.println(buf.readByte());
        System.out.println(buf.readByte());

        //重复读取标记
        buf.markReaderIndex();
        System.out.println(buf.readByte());
        buf.resetReaderIndex();
        System.out.println(buf.readByte());

        ByteBufUtils.log(buf);
    }
}
