/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service;

import com.github.pagehelper.PageInfo;
import com.spring.project.web.core.entity.base.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:18
 */
@Service("userInfoService")
public interface UserInfoService extends UserInfoBaseService {

//    List<UserInfo> queryPage();
//
//    UserInfo getById(int id);
//
//    List<UserInfo> queryAll();
//
//
    long count();


    List<UserInfo> queryByName();
    PageInfo<UserInfo> queryPageByName();
//    List<UserInfo> queryPage();
}
