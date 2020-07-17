/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.core.service;

import com.spring.project.generator.core.entity.Tables;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 10:57
 */
public interface TablesService {
    int insert(Tables record);

    List<Tables> selectAll();

    /**
     * 按数据库名查询
     *
     * @param schema
     * @return
     */
    List<Tables> queryBySchema(String schema);
}
