/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.base.service.impl;

import com.spring.project.web.core.base.entity.UserInfo;
import com.spring.project.web.core.base.mapper.UserInfoBaseMapper;
import com.spring.project.web.core.base.service.UserInfoBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:40
 */
@Service("UserInfoBaseService")
public class UserInfoBaseServiceImpl implements UserInfoBaseService {

    @Autowired
    protected UserInfoBaseMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int n = 0;
        if (id == null) {
            return n;
        }
        n = userInfoMapper.deleteByPrimaryKey(id);
        return n;
    }

    @Override
    public int deleteByPrimaryKeys(Integer[] id) {
        return 0;
    }

    @Override
    public int insert(UserInfo obj) {
        return 0;
    }

    @Override
    public int insertBatch(UserInfo obj) {
        return 0;
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<UserInfo> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(UserInfo obj) {
        return 0;
    }

    @Override
    public long queryCount(UserInfo userInfo) {
        return 0;
    }

    @Override
    public List<UserInfo> queryPage(UserInfo userInfo) {
        return null;
    }
}
