/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.api.config.FilterXssConfigEntity;
import com.spring.project.web.dto.UserInfoDto;
import com.spring.project.web.entity.UserInfo;
import com.spring.project.web.vo.RespResult;
import com.spring.project.web.vo.UserInfoVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-04 11:36
 */
@Controller
public class TestController extends BaseController {

    @Autowired
    private FilterXssConfigEntity filterXssConfigEntity;

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

    @PostMapping(value = "/test2")
    @ResponseBody
    public String test2(Integer id) {
        return "{\"key\":" + id + "}";
    }

    @GetMapping(value = "/dozer")
    @ResponseBody
    public String dozer() {
        UserInfo userInfo = new UserInfo(1, "admin", "123", new Date());
        UserInfoDto userInfoDto = dozerBeanMapper.map(userInfo, UserInfoDto.class);
        System.out.println(userInfoDto.getUserName());
        System.out.println(userInfoDto.getInsertTime());
        return null;
    }


    @GetMapping(value = "/fastjson")
    @ResponseBody()
    public UserInfoVo fastjson(Integer id) {
        UserInfoVo userInfoVo = new UserInfoVo(1, "admin", "123", new Date());
        return userInfoVo;
    }

    @GetMapping(value = "/xss")
    @ResponseBody()
    public RespResult xss(String content) {
        RespResult respResult = new RespResult();
        logger.info(filterXssConfigEntity.getIgnores().size()+"");
        respResult.setData(content);
        return respResult;
    }
    @GetMapping(value = "/static/xss")
    @ResponseBody()
    public RespResult apixss(String content) {
        RespResult respResult = new RespResult();
        logger.info(filterXssConfigEntity.getIgnores().size()+"");
        respResult.setData(content);
        return respResult;
    }

    @PostMapping(value = "/xss2")
    @ResponseBody()
    public RespResult xss2(@RequestBody Map<String, String> content) {
        RespResult respResult = new RespResult();
        respResult.setData(content.get("content"));
        return respResult;
    }
}
