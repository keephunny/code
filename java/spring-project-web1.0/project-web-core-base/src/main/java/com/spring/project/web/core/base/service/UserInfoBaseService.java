/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.base.service;


import com.spring.project.web.core.base.entity.UserInfo;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:40
 */
public interface UserInfoBaseService extends BaseService<UserInfo> {

    long queryCount(UserInfo userInfo);

    List<UserInfo> queryPage(UserInfo userInfo);
}
