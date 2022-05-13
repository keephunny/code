package com.demo.test.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-03 11:28
 *
 * @author
 */
public class TestNetty59 {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(2);

        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        //执行普通任务
        group.next().submit(() -> {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("ok");
        });

        System.out.println("main");

        //定时任务
        group.next().scheduleAtFixedRate(() -> {
            System.out.println("time");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
