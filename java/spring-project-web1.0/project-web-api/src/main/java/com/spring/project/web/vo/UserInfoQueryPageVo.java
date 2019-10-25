/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.vo;

import com.spring.project.web.api.validator.ParamLength;

import javax.validation.constraints.Email;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-28 17:14
 */
public class UserInfoQueryPageVo extends QueryPageVo {

    @ParamLength( 10)
    private String userName;

    @Email(message = "email格式错误")
    @ParamLength( 20)
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
