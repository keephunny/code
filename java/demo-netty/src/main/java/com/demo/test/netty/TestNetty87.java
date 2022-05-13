package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

/**
 * 说明:
 * <p> 将多个ByteBuf组合成一个ByteBuf
 * <br>
 * 创建时间：2022-05-02 18:25
 *
 * @author
 */
public class TestNetty87 {
    public static void main(String[] args) {
        ByteBuf b1 = ByteBufAllocator.DEFAULT.buffer();
        ByteBuf b2 = ByteBufAllocator.DEFAULT.buffer();

        b1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        b2.writeBytes(new byte[]{6, 7, 8, 9, 10});

        CompositeByteBuf byteBufs=ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBufs.addComponents(true,b1,b2);
        ByteBufUtils.log(byteBufs);
    }
}
