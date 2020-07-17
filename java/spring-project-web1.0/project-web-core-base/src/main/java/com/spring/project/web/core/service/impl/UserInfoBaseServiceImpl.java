/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.mapper.base.UserInfoBaseMapper;
import com.spring.project.web.core.service.UserInfoBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:18
 */
@Service("userInfoBaseService")
public class UserInfoBaseServiceImpl implements UserInfoBaseService {

    @Autowired
    private UserInfoBaseMapper userInfoBaseMapper;

    @Override
    public List<UserInfo> queryAll() {
        return userInfoBaseMapper.queryAll();
    }

    @Override
    public PageInfo<UserInfo> queryPage() {
        PageHelper.startPage(0, 2);
        List<UserInfo> queryList = userInfoBaseMapper.queryPage();
        PageInfo pageInfo = new PageInfo<UserInfo>(queryList);
        return pageInfo;
    }

    @Override
    public long queryCount() {
        return userInfoBaseMapper.queryCount();
    }

    @Override
    public UserInfo getByKey(int id) {
        return userInfoBaseMapper.getByKey(id);
    }

    @Override
    public int deleteByKey(int id) {
        return userInfoBaseMapper.deleteByKey(id);
    }

    @Override
    public int deleteByKeys(int[] ids) {
        return userInfoBaseMapper.deleteByKeys(ids);
    }

    @Override
    public int deleteByUnique() {
        return 0;
    }

    @Override
    public int deleteByUniques() {
        return 0;
    }

    @Override
    public int updateByKey(UserInfo userInfo) {
        return userInfoBaseMapper.updateByKey(userInfo);
    }

    @Override
    public int updateByUnique() {
        return 0;
    }

    @Override
    public int insert(UserInfo userInfo) {
        return userInfoBaseMapper.insert(userInfo);
    }

    @Override
    public int insertBatch(List<UserInfo> userInfos) {
        return userInfoBaseMapper.insertBatch(userInfos);
    }
}
