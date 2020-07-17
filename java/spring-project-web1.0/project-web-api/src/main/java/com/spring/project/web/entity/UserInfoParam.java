/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-04 17:46
 */
public class UserInfoParam {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private String userPwd;
    private Date insertTime;

    public UserInfoParam() {
    }

    public UserInfoParam(Integer id, String userName, String userPwd, Date insertTime) {
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
