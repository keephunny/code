/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * webapi接口主程序入口
 *
 * @author 汪强
 * 创建时间 2019-09-04 11:29
 */
@SpringBootApplication
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * webapi接口主程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        logger.info("程序启动");
        SpringApplication.run(Application.class);
    }
}
