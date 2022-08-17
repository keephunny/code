package com.demo.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        test2();

//        System.out.println(buf.readByte());
//        System.out.println(buf.readByte());
//
//        //重复读取标记
//        buf.markReaderIndex();
//        System.out.println(buf.readByte());
//        buf.resetReaderIndex();
//        System.out.println(buf.readByte());


    }

    private static void test1() {
        byte[] by = {-64};
        System.out.println(Integer.toHexString(0xFF & by[0]));


        int n = Integer.parseInt("f0", 16);
        System.out.println(n);
    }

    private static void test2() {
        //外部jar所在位置
        String path = "file:/Users/apple/Documents/GitHub/code/java/netty/testpack/target/test-pack-1.0-SNAPSHOT.jar";
        URLClassLoader urlClassLoader = null;
        Class<?> MyTest = null;
        try {
            //通过URLClassLoader加载外部jar
            urlClassLoader = new URLClassLoader(new URL[]{new URL(path)});
            //获取外部jar里面的具体类对象
            MyTest = urlClassLoader.loadClass("com.test.pack.PackUtil");
            //创建对象实例
            Object instance = MyTest.newInstance();
            //获取实例当中的方法名为show，参数只有一个且类型为string的public方法
            Method method = MyTest.getMethod("add", Integer.class, Integer.class);


            //传入实例以及方法参数信息执行这个方法
            Object ada = method.invoke(instance, 1, 2);

            System.out.println(ada);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //卸载关闭外部jar
            try {
                urlClassLoader.close();
            } catch (IOException e) {
                System.out.println("关闭外部jar失败：" + e.getMessage());
            }
        }

        System.out.println("结束");
    }
}
