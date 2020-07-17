/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;

import com.github.pagehelper.PageInfo;
import com.spring.project.web.api.config.FilterXssConfigEntity;
import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.service.UserInfoService;
import com.spring.project.web.dto.UserInfoDto;
import com.spring.project.web.entity.UserInfoParam;
import com.spring.project.web.vo.RespResult;
import com.spring.project.web.vo.UserInfoQueryPageVo;
import com.spring.project.web.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-04 11:36
 */
@RestController
@Validated
@Api(tags = "测试接口", position = 100)
@RequestMapping(value = "/test")
public class TestController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private FilterXssConfigEntity filterXssConfigEntity;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("/testTable")
    public void testTable() throws Exception {
        Connection conn = sqlSessionFactory.openSession().getConnection();
        DatabaseMetaData data = conn.getMetaData();
        ResultSet rs = data.getColumns(null, null, "user_info", "%");
        while (rs.next()) {
            //打印字段name信息
            logger.info("{}-{}-{}", rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"), rs.getInt("DATA_TYPE"));
        }

//        //遍历resultset
//        List<Map<String, Object>> list = new ArrayList<>();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int count = rsmd.getColumnCount();
//        while (rs.next()) {
//            Map rowData = new HashMap();//声明Map
//            for (int i = 1; i <= count; i++) {
//                System.out.println(rsmd.getColumnName(i) + "=" + rs.getObject(i));
//                rowData.put(rsmd.getColumnName(i), rs.getObject(i));//获取键名及值
//            }
//            list.add(rowData);
//        }
//        System.out.println("list = " + list.size());
    }


    /**
     * thymeleaf测试
     *
     * @param req
     * @return
     */
    @GetMapping(value = "/test0")
    public ModelAndView test0(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", "xxxxxxxxx");
        mv.setViewName("login");
        return mv;
    }

    @GetMapping(value = "/getIP")
    public RespResult getIP() {
        RespResult respResult = new RespResult();

        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        respResult.setData(address.getHostAddress());
        return respResult;
    }


    @PostMapping(value = "/test2")
    @ResponseBody
    public String test2(Integer id) {
        return "{\"key\":" + id + "}";
    }

    @GetMapping(value = "/test3")
    @ResponseBody
    public String test3(@NotBlank String name) {

        return "success:" + name;
    }

    @GetMapping(value = "/test4")
    @ResponseBody
    public String test4(@Valid UserInfoParam userInfo) {

        return "success:";
    }

    @GetMapping(value = "/dozer")
    @ResponseBody
    public String dozer() {
        UserInfoParam userInfo = new UserInfoParam(1, "admin", "123", new Date());
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
        logger.info(filterXssConfigEntity.getIgnores().size() + "");
        respResult.setData(content);
        return respResult;
    }

    @GetMapping(value = "/static/xss")
    @ResponseBody()
    public RespResult apixss(String content) {
        RespResult respResult = new RespResult();
        logger.info(filterXssConfigEntity.getIgnores().size() + "");
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

    @Autowired
    private UserInfoService userInfoService;

    @DeleteMapping("deleteByKey")
    @ApiOperation(value = "根据主键集合批量删除记录")
    public RespResult deleteByKey(Integer id) {
        RespResult respResult = checkID(id);

        return respResult;
    }

    @GetMapping("deleteByKeys")
    @ApiOperation(value = "根据主键删除记录")
    public RespResult deleteByKeys(int[] ids) {
        RespResult respResult = checkIDs(ids);
        userInfoService.deleteByKeys(ids);
        return respResult;
    }


    @GetMapping("/queryAll")
    public RespResult queryAll() {
        RespResult respResult = new RespResult();

        List<UserInfo> queryList = userInfoService.queryAll();
        UserInfo userInfo = userInfoService.getByKey(1);

        UserInfo userInfoInsert = new UserInfo();
        userInfoInsert.setCreateTime(new Date());
        userInfoInsert.setRoleId(2);
        userInfoInsert.setUserName(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
        userInfoService.insert(userInfoInsert);
        logger.info("用户id:{}", userInfoInsert.getId());

        userInfo.setUpdateTime(new Date());
        int n = userInfoService.updateByKey(userInfo);
        logger.info("影响行数{}", n);

//        long count = userInfoService.count();
//        logger.info("总行数{}", count);


        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            UserInfo user = new UserInfo();
            user.setCreateTime(new Date());
            user.setRoleId(2);
            user.setUserName(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20));
            list.add(user);
        }
        n = userInfoService.insertBatch(list);
        logger.info("插入 影响行数 {}", n);
//        logger.info("插入对象id:{}", userInfo.getId());

        n = userInfoService.deleteByKey(61);
        logger.info("删除影响行:{}", n);
        int[] ids = {100, 200, 300, 62};
        n = userInfoService.deleteByKeys(ids);
        logger.info("删除影响行:{}", n);
        respResult.setData(userInfo);
        return respResult;
    }


    @GetMapping("/queryPage")
    public RespResult queryPage() {
        RespResult respResult = new RespResult();
        PageInfo pageInfo = userInfoService.queryPageByName();
        respResult.setData(pageInfo.getList());
        respResult.setTotal(pageInfo.getTotal());
        long n = userInfoService.count();
        logger.info("总行数：{}", n);
        respResult.setData(pageInfo.getList());
        return respResult;
    }


    @GetMapping("/exportData")
    public RespResult exportData(HttpServletResponse response) {
        RespResult respResult = new RespResult();
        //List<UserInfoExtend> queryList = userInfoService.selectAll();

//        ExportParams params = new ExportParams();
//        params.setHeight((short) 8);
////        params.setStyle(ExcelExportMyStylerImpl.class);
//        try {
//            Workbook workbook = ExcelExportUtil.exportExcel(params, UserInfoExtend.class, queryList);
//            String fileName = "headTitle ";
//            fileName = URLEncoder.encode(fileName, "UTF8");
//            response.setContentType("application/vnd.ms-excel;chartset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
//            ServletOutputStream out = response.getOutputStream();
//            workbook.write(out);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        respResult.setData(queryList);
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
