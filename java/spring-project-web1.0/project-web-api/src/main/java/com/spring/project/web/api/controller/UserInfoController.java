/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.core.service.UserInfoService;
import com.spring.project.web.vo.RequestQueryPageVo;
import com.spring.project.web.vo.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * UserInfoController
 *
 * @author w
 * 创建时间 2019-09-06 11:36
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/queryAll")
    public RespResult queryAll(@Valid RequestQueryPageVo queryPageVo, BindingResult bindingResult) {
        RespResult responseVo = checkParam(bindingResult);
        if (responseVo.getCode() != HttpStatus.OK.value()) {
            return responseVo;
        }
        logger.info("{} {}", queryPageVo.getBeginDate(), queryPageVo.getStart());
        if (queryPageVo.getStart() == 0) {
            responseVo.setCode(HttpStatus.BAD_REQUEST.value(), "用户id已存在");
        }
        return responseVo;
        //return userInfoService.queryAll();
    }

}
