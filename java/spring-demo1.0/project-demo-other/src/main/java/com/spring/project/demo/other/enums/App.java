/*
 * Copyright (c) 2019.
 */
package com.spring.project.demo.other.enums;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-05 14:06
 */
public class App {
    public static void main(String[] args) {
        TypeEnum typeEnum=TypeEnum.valueOf("CANCEL");

        System.out.println(typeEnum.getStr());

        try {
            throw new Exception("sdffdsdfs");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
