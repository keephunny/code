/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import javax.swing.table.TableColumn;
import java.util.List;
import java.util.Map;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-26 17:44
 */
public class TableEntity {
    private String schema;
    private String tableName;
    private String tableType;
    private String className;
    private String classNameNew;
    private String tableComment;

    private List<TableColumnEntity> columnsAll;
    private List<TableColumnEntity> columnsPrimaryKey;
    private Map<String, List<TableColumnEntity>> columnsUnique;

    private Map<String, String> primaryKeys;
    private Map<String, List<String>> indexUniques;

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNameNew() {
        return classNameNew;
    }

    public void setClassNameNew(String classNameNew) {
        this.classNameNew = classNameNew;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }


    public List<TableColumnEntity> getColumnsAll() {
        return columnsAll;
    }

    public void setColumnsAll(List<TableColumnEntity> columnsAll) {
        this.columnsAll = columnsAll;
    }

    public List<TableColumnEntity> getColumnsPrimaryKey() {
        return columnsPrimaryKey;
    }

    public void setColumnsPrimaryKey(List<TableColumnEntity> columnsPrimaryKey) {
        this.columnsPrimaryKey = columnsPrimaryKey;
    }

    public Map<String, List<TableColumnEntity>> getColumnsUnique() {
        return columnsUnique;
    }

    public void setColumnsUnique(Map<String, List<TableColumnEntity>> columnsUnique) {
        this.columnsUnique = columnsUnique;
    }

    public Map<String, String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(Map<String, String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public Map<String, List<String>> getIndexUniques() {
        return indexUniques;
    }

    public void setIndexUniques(Map<String, List<String>> indexUniques) {
        this.indexUniques = indexUniques;
    }

    public String getColumnsName() {
        StringBuilder sb = new StringBuilder();
        for (TableColumnEntity column : columnsAll) {
            sb.append(",");
            sb.append(column.getColumnName());
        }
        return sb.toString().substring(1);
    }
}
