/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.core.entity.UserInfo;
import com.spring.project.web.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserInfoController
 *
 * @author 汪强
 * 创建时间 2019-09-06 11:36
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/queryAll")
    public UserInfo queryAll() {
        return userInfoService.queryAll();
    }

}
