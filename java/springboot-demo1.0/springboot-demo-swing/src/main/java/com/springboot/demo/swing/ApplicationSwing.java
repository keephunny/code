/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-22 17:19
 */
@SpringBootApplication
@EnableScheduling
public class ApplicationSwing {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSwing.class);
    }
}
