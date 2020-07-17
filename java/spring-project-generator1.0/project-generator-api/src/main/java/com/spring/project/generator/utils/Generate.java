/*
 * Copyright (c) 2020.
 */
package com.spring.project.generator.utils;

import java.lang.annotation.*;

/**
 * 替换字符串与属性对应关系
 *
 * @author 汪强
 * 创建时间 2020-04-25 14:06
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Generate {
    String value() default "";
}
