/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-28 17:16
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ParamLengthValidator.class})
public @interface ParamLength {
    // 当验证不通过时的提示信息
    String message() default "不允许超过长度{value}";

    int value();

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
