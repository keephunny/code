/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service;

import com.github.pagehelper.PageInfo;
import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.mapper.sql.UserInfoSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 16:18
 */
@Service("userInfoService")
public interface UserInfoBaseService {

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    List<UserInfo> queryAll();

    PageInfo<UserInfo> queryPage();

    long queryCount();

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return 返回值
     */
    UserInfo getByKey(int id);

    /**
     * 根据id删除记录
     *
     * @param id
     * @return 影响行数
     */
    int deleteByKey(int id);

    /**
     * 根据id集合指量删除记录
     *
     * @param ids
     * @return 影响行数
     */
    int deleteByKeys(int[] ids);

    int deleteByUnique();

    int deleteByUniques();

    int updateByKey(UserInfo userInfo);

    int updateByUnique();

    int insert(UserInfo userInfo);

    int insertBatch(List<UserInfo> userInfos);

}
