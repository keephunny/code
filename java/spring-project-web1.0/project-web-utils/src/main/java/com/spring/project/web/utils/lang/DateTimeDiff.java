/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.utils.lang;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-28 16:36
 */
public class DateTimeDiff {
    /**
     * 间隔年
     */
    private int year;
    /**
     * 间隔月
     */
    private int month;
    /**
     * 间隔天
     */
    private int day;
    /**
     * 间隔小时
     */
    private int hour;
    /**
     * 间隔分钟
     */
    private int minute;
    /**
     * 间隔秒
     */
    private int seconds;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
