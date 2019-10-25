/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.core.entity.UserInfo;
import com.spring.project.web.core.service.UserInfoService;
import com.spring.project.web.vo.QueryPageVo;
import com.spring.project.web.vo.RespResult;
import com.spring.project.web.vo.UserInfoQueryPageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * UserInfoController
 *
 * @author w
 * 创建时间 2019-09-06 11:36
 */
@RestController
@RequestMapping(value = "/userInfo")
@Api(value = "1.userInfoController", description = "用户管理")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @DeleteMapping("deleteByKey")
    @ApiOperation(value = "根据主键集合批量删除记录")
    public RespResult deleteByKey(Integer id) {
         RespResult respResult = checkID(id);

        return respResult;
    }

    @DeleteMapping("deleteByKeys")
    @ApiOperation(value = "根据主键删除记录")
    public RespResult deleteByKeys(Integer[] id) {
        RespResult respResult = checkIDs(id);

        return respResult;
    }


    @GetMapping("/queryAll")
    public RespResult queryAll() {
        RespResult respResult = new RespResult();
        UserInfo userInfo = userInfoService.queryAll();
        respResult.setData(userInfo);

        return respResult;
    }

    @GetMapping("/test")
    public RespResult test(@Valid UserInfoQueryPageVo queryPageVo, BindingResult bindingResult) {
        RespResult respResult = checkParam(bindingResult);
        if (respResult.getCode() != HttpStatus.OK.value()) {
            return respResult;
        }
        respResult = checkBeginEndDateDiff(queryPageVo.getBeginDate(), queryPageVo.getEndDate(), "year", 1);
        if (respResult.getCode() != HttpStatus.OK.value()) {
            return respResult;
        }

        System.out.println(queryPageVo.getBeginDate());
        System.out.println(queryPageVo.getEndDate());
        logger.info("{} {}", queryPageVo.getBeginDate(), queryPageVo.getStart());
        if (queryPageVo.getStart() == 0) {
            respResult.setCode(HttpStatus.BAD_REQUEST.value(), "用户id已存在");
        }
        return respResult;
        //return userInfoService.queryAll();
    }
}
