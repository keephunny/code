/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.base.mapper;


import java.util.List;

/**
 * 数据库操作基类方法
 * 基于表主键是Integer类型，如果主键是非Integer则不要继承
 *
 * @author 汪强
 * 创建时间 2019-07-15 17:16
 */
public interface BaseMapper<T> {

    /**
     * 按照主键删除记录
     *
     * @param id id
     * @return 返回值
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 按照主键集合批量删除记录
     *
     * @param id id
     * @return 返回值
     */
    int deleteByPrimaryKeys(Integer[] id);

    /**
     * 插入记录
     *
     * @param obj 对象
     * @return 返回值
     */
    int insert(T obj);

    /**
     * 批量插入记录
     *
     * @param obj 对象
     * @return 返回值
     */
    int insertBatch(T obj);

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return 返回值
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 查询全部记录
     *
     * @return 返回值
     */
    List<T> selectAll();

    /**
     * 根据主键修改记录
     *
     * @param obj 对象
     * @return 返回值
     */
    int updateByPrimaryKey(T obj);
}
