/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.core.service.impl;

import com.spring.project.generator.core.entity.Columns;
import com.spring.project.generator.core.mapper.ColumnsMapper;
import com.spring.project.generator.core.service.ColumnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 11:03
 */
@Service("columnsService")
public class ColumnsServiceImpl implements ColumnsService {
    @Autowired
    private ColumnsMapper columnsMapper;

    @Override
    public int insert(Columns columns) {
        return columnsMapper.insert(columns);
    }

    @Override
    public List<Columns> selectAll() {
        return columnsMapper.selectAll();
    }

    /**
     * 按数据库名查询
     *
     * @param schema    数据库名
     * @param tableName 表名
     * @return
     */
    @Override
    public List<Columns> queryBySchema(String schema, String tableName) {
        return columnsMapper.queryBySchema(schema, tableName);
    }
}
