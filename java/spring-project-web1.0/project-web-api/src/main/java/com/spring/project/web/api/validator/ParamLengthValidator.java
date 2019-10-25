/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-28 17:24
 */
public class ParamLengthValidator implements ConstraintValidator<ParamLength, Object> {

    private int value;

    /**
     * 初始化方法， 在执行isValid 方法前，会先执行此方法
     *
     * @param paramLength 注解信息模型，可以从该模型中获取注解类中定义的一些信息，如默认值等
     * @date 2019/1/19 11:27
     */
    @Override
    public void initialize(ParamLength paramLength) {
        this.value = paramLength.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || "".equals(value.toString().trim())) {
            return true;
        }

        if (value instanceof String) {
            String str = (String) value;
            if (str.length() <= this.value) {
                return true;
            }
        } else if (value instanceof Integer) {
            //return contains.contains(String.valueOf(value));
        }
        return false;
    }
}
