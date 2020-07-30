package com.yang.serialport.manager;

public class Test {
    public static void main(String[] args) {
        for (double i = 0; i < 10; i += 0.1) {

            double d = Math.abs(Math.sin(i));
            System.out.println(d * 100);
        }
    }
}
