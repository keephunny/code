/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.service.UserInfoService;
import com.spring.project.web.vo.QueryPageVo;
import com.spring.project.web.vo.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * UserInfoController
 *
 * @author w
 * 创建时间 2019-09-06 11:36
 */
@RestController
@RequestMapping(value = "/userInfo")
@Api(tags = "用户管理", value = "用户管理", description = "用户管理")
public class UserInfoController extends BaseController {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    /**
     * 用户service
     */
    @Autowired
    private UserInfoService userInfoService;

    @DeleteMapping("/deleteByKey")
    @ApiOperation(value = "根据主键集合批量删除记录")
    public RespResult deleteByKey(Integer id) {
        RespResult respResult = checkID(id);
        return respResult;
    }

    @GetMapping("/deleteByKeys")
    @ApiOperation(value = "根据主键删除记录")
    public RespResult deleteByKeys(int[] ids) {
        RespResult respResult = checkIDs(ids);
        userInfoService.deleteByKeys(ids);
        return respResult;
    }


    @GetMapping("/queryAll")
    @ApiOperation("查询全部")
    public RespResult queryAll() {
        RespResult respResult = new RespResult();
        List<UserInfo> queryList = userInfoService.queryAll();
        respResult.setData(queryList);
        return respResult;
    }


    @PostMapping(value = "/queryPage")
    @ApiOperation("分页查询")
    public RespResult queryPage(String id, HttpServletRequest request) {
        RespResult respResult = new RespResult();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(request.getParameter("id"));
        respResult.setData(1L);
        return respResult;
    }


    @GetMapping("/exportData")
    @ApiOperation("数据导出")
    public RespResult exportData(HttpServletResponse response) {
        RespResult respResult = new RespResult();
        return respResult;
    }

}
