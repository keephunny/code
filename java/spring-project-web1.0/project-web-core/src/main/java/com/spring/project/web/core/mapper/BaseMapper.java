/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao基类 封装了对表的基本增删改查功能 与mybatis代码生成工具 mapper.xml对应
 * 将公共通用方法抽取出来，方便业务代码的更新
 * 各子类需要继承实现
 *
 * @author 汪强
 * 创建时间 2019-10-25 16:40
 */
@Component
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
