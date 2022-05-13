package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-02 16:28
 *
 * @author
 */
public class TestNetty79 {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buf);
        ByteBufUtils.log(buf);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append("a");
        }
        buf.writeBytes(sb.toString().getBytes());
        System.out.println(buf);
        ByteBufUtils.log(buf);
    }

}
