package com.demo.test.netty;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-06-03 17:15
 *
 * @author
 */
public class TestApp {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);

        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时任务:" + System.currentTimeMillis());
            }
        }, 0, 10, TimeUnit.SECONDS);

        for (ColorEnum c : ColorEnum.values()) {
            System.out.print(c.ordinal());
            System.out.print(c.name());
            System.out.println(c.toString());
        }

        EnumMap<ColorEnum, String> enumMap = new EnumMap<ColorEnum, String>(ColorEnum.class);
        enumMap.put(ColorEnum.GREEN, "green.com");
        enumMap.put(ColorEnum.RED, "red.com");
        System.out.println(enumMap.get(ColorEnum.GREEN));

        //EnumSet<ColorEnum> enumSet = EnumSet.allOf(EnumSet.class);

    }
}

enum ColorEnum {
    RED("红色", 1), GREEN("绿色", 2), WHITE("白色", 3), YELLOW("黄色", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    ColorEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.index + "-" + this.name;
    }
}