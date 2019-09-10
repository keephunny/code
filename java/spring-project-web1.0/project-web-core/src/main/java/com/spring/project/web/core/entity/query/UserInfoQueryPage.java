/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.entity.query;

/**
 * 用户查询参数
 *
 * @author w
 * 创建时间 2019-09-05 16:54
 */
public class UserInfoQueryPage extends QueryPage {
    /**
     * 用户名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
