/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-05-26 9:26
 */
public class LoggerQueue {
    private final Logger logger = LoggerFactory.getLogger(LoggerQueue.class);

    //队列大小
    public static final int QUEUE_MAX_SIZE = 10000;
    private static LoggerQueue alarmMessageQueue = new LoggerQueue();
    //阻塞队列
    private BlockingQueue blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    private LoggerQueue() {
    }

    public static LoggerQueue getInstance() {
        return alarmMessageQueue;
    }

    /**
     * 消息入队
     *
     * @param log
     * @return
     */
    public boolean push(LoggerMessage log) {
        return this.blockingQueue.add(log);//队列满了就抛出异常，不阻塞
    }

    /**
     * 消息出队
     *
     * @return
     */
    public LoggerMessage poll() {
        LoggerMessage result = null;
        try {
            result = (LoggerMessage) this.blockingQueue.take();
        } catch (InterruptedException e) {
            logger.error("", e);
        }
        return result;
    }
}
