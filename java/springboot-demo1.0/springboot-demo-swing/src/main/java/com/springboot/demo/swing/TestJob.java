/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-22 17:24
 */
@Component
public class TestJob {
    private final Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Scheduled(cron = "*/2 * * * * ?")
    public void testLog() {
        logger.info("info日志");
        logger.warn("warn日志");
    }
}
