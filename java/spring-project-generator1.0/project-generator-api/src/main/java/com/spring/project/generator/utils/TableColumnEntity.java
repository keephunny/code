/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-26 17:44
 */
public class TableColumnEntity {
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;

    /**
     * 数据字段原型类型
     */
    private String columnTypeName;

    /**
     * 是否主键
     */
    private boolean columnIsKey;

    /**
     * 是否为空
     */
    private boolean columnIsNull;

    /**
     * 自增列
     */
    private boolean columnAutoIncrement;

    private boolean columnGenerated;
    /**
     * 字段jdbcType类型
     */
    private int jdbcType;
    /**
     * 字段jdbcType类型名称
     */
    private String jdbcTypeName;

    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * 字段长度
     */
    private int columnSize;

    /**
     * 小数位
     */
    private int columnDecimalDigits;

    /**
     * 转换java属性名 大写
     */
    private String propertyName;

    /**
     * 转换java属性名 首字母小写
     */
    private String propertyNameNew;

    /**
     * 属性数据类型 全称
     */
    private String javaTypeFully;
    /**
     * 属性数据类型 简称
     */
    private String javaTypeShort;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public boolean isColumnIsKey() {
        return columnIsKey;
    }

    public void setColumnIsKey(boolean columnIsKey) {
        this.columnIsKey = columnIsKey;
    }

    public boolean isColumnIsNull() {
        return columnIsNull;
    }

    public void setColumnIsNull(boolean columnIsNull) {
        this.columnIsNull = columnIsNull;
    }

    public boolean isColumnAutoIncrement() {
        return columnAutoIncrement;
    }

    public void setColumnAutoIncrement(boolean columnAutoIncrement) {
        this.columnAutoIncrement = columnAutoIncrement;
    }

    public boolean isColumnGenerated() {
        return columnGenerated;
    }

    public void setColumnGenerated(boolean columnGenerated) {
        this.columnGenerated = columnGenerated;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyNameNew() {
        return propertyNameNew;
    }

    public void setPropertyNameNew(String propertyNameNew) {
        this.propertyNameNew = propertyNameNew;
    }

    public String getJavaTypeFully() {
        return javaTypeFully;
    }

    public void setJavaTypeFully(String javaTypeFully) {
        this.javaTypeFully = javaTypeFully;
    }

    public String getJavaTypeShort() {
        return javaTypeShort;
    }

    public void setJavaTypeShort(String javaTypeShort) {
        this.javaTypeShort = javaTypeShort;
    }

    public int getColumnDecimalDigits() {
        return columnDecimalDigits;
    }

    public void setColumnDecimalDigits(int columnDecimalDigits) {
        this.columnDecimalDigits = columnDecimalDigits;
    }
}
