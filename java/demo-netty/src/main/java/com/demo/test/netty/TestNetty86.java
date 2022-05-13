package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-02 17:51
 *
 * @author
 */
public class TestNetty86 {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{1, 2, 3, 4, 5, 6, 7, 8,9,10});

        ByteBufUtils.log(buf);

        ByteBuf b1=buf.slice(0,5);
        ByteBuf b2=buf.slice(5,5);
        ByteBufUtils.log(b1);
        ByteBufUtils.log(b2);

        System.out.println("--------");
        buf.setByte(0,0);

        //buf.release();
        //ByteBufUtils.log(buf);
        ByteBufUtils.log(b1);
    }
}
