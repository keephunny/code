/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.dto.UserInfoDto;
import com.spring.project.web.entity.UserInfo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-04 11:36
 */
@Controller
public class TestController {

    /**
     * dozerBeanMapper对象转换
     */
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * thymeleaf测试
     *
     * @param req
     * @return
     */
    @GetMapping(value = "/test")
    public ModelAndView test(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", "xxxxxxxxx");
        mv.setViewName("login");
        return mv;
    }

    @GetMapping(value = "/test2")
    @ResponseBody
    public String test2(HttpServletRequest req) {
        return "{\"key\":1000}";
    }

    @GetMapping(value = "/dozer")
    @ResponseBody
    public String dozer() {
        UserInfo userInfo = new UserInfo(1, "admin", "123", new Date());
        UserInfoDto userInfoDto = dozerBeanMapper.map(userInfo, UserInfoDto.class);
        System.out.println(userInfoDto.getUserName());
        return null;
    }
}
