/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 接口返回包装对象
 * 200:成功
 * 500:失败
 *
 * @author w
 * 创建时间 2019-07-10 18:01
 */
public class RespResult {
    /**
     * 成功状态码
     */
    private final Integer SUCCESS_CODE = 200;
    /**
     * 失败状态码
     */
    private final Integer ERROR_CODE = 500;
    /**
     * 成功提示信息
     */
    private final String SUCCESS_MSG = "操作成功";
    /**
     * 失败提示信息
     */
    private final String ERROR_MSG = "操作失败";

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回对象
     */
    private Object data;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 构造函数
     */
    public RespResult() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  提示信息
     */
    public RespResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造函数
     *
     * @param code 状态码
     */
    public RespResult(int code) {
        this.code = code;
        if (code == SUCCESS_CODE) {
            this.msg = SUCCESS_MSG;
        } else if (code == ERROR_CODE) {
            this.msg = ERROR_MSG;
        }
    }

    /**
     * 获取状态码
     *
     * @return 返回状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     */
    public void setCode(int code) {
        this.code = code;
//        if (code == SUCCESS_CODE) {
//            this.msg = SUCCESS_MSG;
//        } else if (code == ERROR_CODE) {
//            this.msg = ERROR_MSG;
//        }
    }

    /**
     * 设置状态码
     * @param code 状态码
     * @param msg 信息
     */
    public void setCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取提示信息
     *
     * @return 返回提示信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示信息
     *
     * @param msg 提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取对象
     *
     * @return 获取对象
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置对象
     *
     * @param data 对象
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 获取记录总数 分页常用
     *
     * @return 记录总数
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置记录总数 分页常用
     *
     * @param total 记录总数
     */
    public void setTotal(Long total) {
        this.total = total;
    }

}
