/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-27 13:59
 */
@Component
public class JobProcess {
    private final Logger logger = LoggerFactory.getLogger(JobProcess.class);

    @Scheduled(cron = "0/2 * * * * ?")
    public void reportLine() {
        logger.info("info日志");
        logger.warn("warn日志");
    }
}
