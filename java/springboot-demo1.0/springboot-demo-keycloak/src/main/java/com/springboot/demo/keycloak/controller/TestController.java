/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-08-07 11:31
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    @GetMapping("test1")
    public void test1() {

    }
}
