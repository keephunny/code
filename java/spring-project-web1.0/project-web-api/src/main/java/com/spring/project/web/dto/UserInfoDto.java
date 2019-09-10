/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.dto;

import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-04 17:47
 */
public class UserInfoDto {
    private String userName;
    private String insertTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }
}
