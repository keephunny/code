/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.mapper.UserInfoMapper;
import com.spring.project.web.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:18
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends UserInfoBaseServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    //    @Override
//    public List<UserInfo> queryPage() {
//        PageHelper.startPage(0, 2);
//        List<UserInfo> queryList = userInfoMapper.queryPage();
//        PageInfo pageInfo = new PageInfo<UserInfo>(queryList);
//        return queryList;
//    }
//
//
//    @Override
//    public UserInfo getById(int id) {
//        return userInfoMapper.getById(id);
//    }
//
//    @Override
//    public List<UserInfo> queryAll() {
//        return userInfoMapper.queryAll();
//    }
//
//
    @Override
    public long count() {
        return userInfoMapper.count();
    }


    @Override
    public List<UserInfo> queryByName() {
        return userInfoMapper.queryByName();
    }

    @Override
    public PageInfo<UserInfo> queryPageByName() {
        PageHelper.startPage(0, 2);
        List<UserInfo> queryList = userInfoMapper.queryPageByName();
        PageInfo pageInfo = new PageInfo<UserInfo>(queryList);
        return pageInfo;
    }


//    @Override
//    public List<UserInfo> queryPage() {
//        return userInfoMapper.queryPage();
//    }
}
