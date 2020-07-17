/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.core.service;

import com.spring.project.generator.core.entity.Columns;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 11:03
 */
public interface ColumnsService {
    int insert(Columns record);

    List<Columns> selectAll();

    /**
     * 按数据库名查询
     *
     * @param schema    数据库名
     * @param tableName 表名
     * @return
     */
    List<Columns> queryBySchema(String schema,  String tableName);
}
