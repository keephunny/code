/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.mapper.base;

import com.spring.project.web.core.entity.base.UserInfo;
import com.spring.project.web.core.mapper.sql.UserInfoSql;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInfoMapper
 * <p>
 * 1、统计类
 * 2、查询类
 * 3、获取对像类
 * ByPrimaryKey
 * ByUnique
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:41
 */
@Repository
public interface UserInfoBaseMapper {

    List<UserInfo> queryAll();

    List<UserInfo> queryPage();


    List<UserInfo> queryByExample(UserInfo userInfo);

    /**
     * 查询总行数
     *
     * @return 总行数
     */
    long queryCount();

    long queryCountByExample();

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return 返回值
     */
//    @ResultMap("com.spring.project.web.core.mapper.base.UserInfoBaseMapper.BaseResultMap")
//    @Select("select * from user_info where id=#{id}")
    UserInfo getByKey(int id);

    UserInfo getByUnique();

    /**
     * 根据key删除记录
     *
     * @param id
     * @return 影响行数
     */
    int deleteByKey(int id);

    /**
     * 根据key集合批量删除记录
     *
     * @param ids
     * @return 影响行数
     */
    int deleteByKeys(int[] ids);

    int deleteByUnique();

    int deleteByUniques();

    int updateByKey(UserInfo userInfo);

    int updateByUnique();

    /**
     * 插入
     *
     * @param userInfo 对象
     * @return 自增主键或影响行数
     */
    Integer insert(UserInfo userInfo);

    /**
     * 批量插入
     *
     * @param userInfos 对象集合
     * @return 影响行数
     */
    int insertBatch(List<UserInfo> userInfos);

}
