/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.entity.query;

import java.util.Date;

/**
 * 查询参数基类
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:54
 */
public class QueryPage {

    /**
     * 分页查询起页
     */
    private Integer start = 0;

    /**
     * 分页查询止页
     */
    private Integer length = 20;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 截止时间
     */
    private Date endDate;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 获取开始页
     *
     * @return 开始页
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置开始页
     *
     * @param start 开始页
     */
    public void setStart(Integer start) {
        //判断start值是否正确，不正确的全部转成0
        if (start == null || start < 0) {
            start = 0;
        }
        this.start = start;
    }

    /**
     * 设置分页长度
     *
     * @return 分页长度
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置分页长度
     *
     * @param length 分页长度
     */
    public void setLength(Integer length) {
        /**
         * 判断lenght值是否正确
         * 不允许一次查询超过1000条
         * 不允许为空或小于 否则全部转成20
         */
        if (length == null || length < 0) {
            length = 0;
        } else if (length > 1000) {
            length = 20;
        }
        this.length = length;
    }

    /**
     * 获取开始时间
     *
     * @return 开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始时间
     *
     * @param beginDate 开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取截止时间
     *
     * @return 截止时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置截止时间
     *
     * @param endDate 截止时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
