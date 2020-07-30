/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing.log;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-05-26 9:28
 */
public class LoggerMessage {
    private String body;
    private String timestamp;
    private String threadName;
    private String className;
    private String level;
    private String exception;
    private String cause;

    public LoggerMessage() {
    }

    public LoggerMessage(String body, String timestamp, String threadName, String className, String level, String exception, String cause) {
        this.body = body;
        this.timestamp = timestamp;
        this.threadName = threadName;
        this.className = className;
        this.level = level;
        this.exception = exception;
        this.cause = cause;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "LoggerMessage{" +
                "body='" + body + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", threadName='" + threadName + '\'' +
                ", className='" + className + '\'' +
                ", level='" + level + '\'' +
                ", exception='" + exception + '\'' +
                ", cause='" + cause + '\'' +
                '}';
    }
}
