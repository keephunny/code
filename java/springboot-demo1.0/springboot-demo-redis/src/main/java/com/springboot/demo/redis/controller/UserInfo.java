/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.redis.controller;

import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-08-03 15:45
 */
public class UserInfo {
    private int id;
    private String userName;
    private Date insertTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
