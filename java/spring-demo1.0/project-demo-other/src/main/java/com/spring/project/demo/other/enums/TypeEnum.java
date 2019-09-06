/*
 * Copyright (c) 2019.
 */
package com.spring.project.demo.other.enums;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-05 11:24
 */
public enum TypeEnum {
    CANCEL("取消", 1),
    CONFIRM("确认", 2),
    WARM("警告", 3),
    ERROR("错误", 4);

    private String str;
    private int value;

    TypeEnum(String str, int value) {
        this.str = str;
        this.value = value;
    }

    public String getStr() {
        return str;
    }

    public int getValue() {
        return value;
    }}
