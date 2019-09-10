/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service.impl;

import com.spring.project.web.core.entity.UserInfo;
import com.spring.project.web.core.mapper.UserInfoMapper;
import com.spring.project.web.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:18
 */
@Service("userInfoService")
public class UserInfoServiceImpl  implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo queryAll() {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        return userInfo;
    }
}
