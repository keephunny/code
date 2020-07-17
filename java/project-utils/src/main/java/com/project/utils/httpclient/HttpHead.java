/*
 * Copyright (c) 2019.
 */
package com.project.utils.httpclient;

import java.lang.annotation.*;

/**
 * //添加说明
 *
 * @author 汪强
 * 创建时间 2019-10-17 9:45
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpHead {

    String value() default "";
}