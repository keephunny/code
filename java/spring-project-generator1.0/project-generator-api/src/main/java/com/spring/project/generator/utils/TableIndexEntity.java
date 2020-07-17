/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-28 19:57
 */
public class TableIndexEntity {
    private String indexName;
    private List<String> columns;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
