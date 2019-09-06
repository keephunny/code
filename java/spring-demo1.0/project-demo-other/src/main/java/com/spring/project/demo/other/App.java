package com.spring.project.demo.other;

import com.spring.project.demo.other.enums.TypeEnum;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        System.out.println(TypeEnum.CANCEL);
        System.out.println(TypeEnum.CANCEL.getStr());
        System.out.println(TypeEnum.CANCEL.name());
        System.out.println(TypeEnum.CANCEL.ordinal());
        System.out.println("Hello World!");
    }
}
