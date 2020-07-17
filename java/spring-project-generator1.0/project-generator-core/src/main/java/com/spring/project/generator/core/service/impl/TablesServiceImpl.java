/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.core.service.impl;

import com.spring.project.generator.core.entity.Tables;
import com.spring.project.generator.core.mapper.TablesMapper;
import com.spring.project.generator.core.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-24 10:57
 */
@Service("tableService")
public class TablesServiceImpl implements TablesService {
    @Autowired
    private TablesMapper tablesMapper;

    @Override
    public int insert(Tables tables) {
        return tablesMapper.insert(tables);
    }

    @Override
    public List<Tables> selectAll() {
        return tablesMapper.selectAll();
    }

    /**
     * 按数据库名查询
     *
     * @param schema
     * @return
     */
    @Override
    public List<Tables> queryBySchema(String schema) {
        return tablesMapper.queryBySchema(schema);
    }
}
