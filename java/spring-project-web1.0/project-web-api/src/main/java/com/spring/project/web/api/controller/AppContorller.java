/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.vo.RespResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共提示模块
 * 因为系统的所有接口都会涉及到业务权限分配，如果把供提示模版放在业务模块里如果用户无权限的话则提示信息则无法正常显示。
 * 1.获取当前用户信息
 * 2.登录
 * 3.验证码
 * 4.当前时间
 * 5.友好提示
 *
 * @author 汪强
 * 创建时间 2019-09-12 15:02
 */
@RestController
@RequestMapping("/app")
public class AppContorller {


    @GetMapping("/info")
    public RespResult info() {
        RespResult respResult = new RespResult();
        respResult.setCode(HttpStatus.UNAUTHORIZED.value(), "无权限");
        return respResult;
    }

    //登录
}
