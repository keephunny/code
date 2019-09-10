/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义validator
 *
 * @author w
 * 创建时间 2019-09-09 17:24
 */
public class QueryPageValidator implements ConstraintValidator<QueryKey, Object> {

    /**
     * 错误提示信息
     */
    private String contains;

    /**
     * 初始化方法， 在执行isValid 方法前，会先执行此方法
     *
     * @param queryKey 注解信息模型，可以从该模型中获取注解类中定义的一些信息，如默认值等
     * @date 2019/1/19 11:27
     */
    @Override
    public void initialize(QueryKey queryKey) {
        this.contains = queryKey.contains();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || "".equals(value.toString().trim())) {
            return true;
        }
        if (value instanceof String) {
            String strMessgae = (String) value;
            return strMessgae.contains(contains);
        } else if (value instanceof Integer) {
            return contains.contains(String.valueOf(value));
        }
        return false;
    }
}
