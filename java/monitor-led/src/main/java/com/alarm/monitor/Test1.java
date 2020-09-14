package com.alarm.monitor;

public class Test1 {
    public static void main(String[] args) {
        long n1 = System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis());
        try {
//            Thread.sleep(100);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - n1);
    }
}
