/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.logback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-27 11:36
 */
@SpringBootApplication
@EnableScheduling
public class ApplicationLogback {
    /**
     * 主程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationLogback.class);
    }
}
