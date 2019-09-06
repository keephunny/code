/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.vo;

import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:29
 */
public class UserInfoVo {
    private Integer id;
    private String userName;
    private String userPwd;
    private Date insertTime;

    public UserInfoVo() {
    }

    public UserInfoVo(Integer id, String userName, String userPwd, Date insertTime) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
        this.insertTime = insertTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}
