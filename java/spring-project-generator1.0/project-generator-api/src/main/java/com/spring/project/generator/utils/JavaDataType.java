/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-04-25 17:49
 */
public class JavaDataType {

    private String javaType;
    private String dbType;
    private String jdbcType;

    public JavaDataType() {
    }

    public JavaDataType(String javaType, String dbType, String jdbcType) {
        this.javaType = javaType;
        this.dbType = dbType;
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
