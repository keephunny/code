/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.mapper;

import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.mapper.base.UserInfoBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:17
 */
@Repository
public interface UserInfoMapper extends UserInfoBaseMapper {

    long count();

    List<UserInfo> queryByName();
    List<UserInfo> queryPageByName();
}
