/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.service.impl;

import com.spring.project.web.core.mapper.BaseMapper;
import com.spring.project.web.core.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * service基类 封装了对表的基本增删改查功能 与mybatis代码生成工具 mapper.xml对应
 * 将公共通用方法抽取出来，方便业务代码的更新
 * 各子类需要继承实现
 * 定义日志工具，子类不需要重复实现
 * 定义basemapper实现基本增删改查
 * 如果有个性化需要，需要子类@Override实现
 *
 * @author 汪强
 * 创建时间 2019-10-25 17:18
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKeys(Integer[] id) {
        return baseMapper.deleteByPrimaryKeys(id);
    }

    @Override
    public int insert(T obj) {
        return baseMapper.insert(obj);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(T obj) {
        return baseMapper.updateByPrimaryKey(obj);
    }
}
