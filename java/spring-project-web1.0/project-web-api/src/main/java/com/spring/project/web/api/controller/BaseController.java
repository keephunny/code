/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;


import com.spring.project.web.vo.RespResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 11:37
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数异常检模
     *
     * @param bindingResult
     * @return
     */
    protected RespResult checkParam(BindingResult bindingResult) {
        RespResult respResult = new RespResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            String msg = null;
            for (FieldError ferr : list) {
                //针对typeMismatch.java.lang.Integer typeMismatch.java.util.Date 系统类型转换 系统异常信息不抛出
                //针对自定定义类型Min.java.lang.Integer  NotNull.java.lang.Integer 抛出getDefaultMessage();
                //ferr.getCodes() ferr.getArguments() getAllErrors()
                if ("typeMismatch".equals(ferr.getCode())) {
                    msg = "参数错误[" + ferr.getField() + "=" + ferr.getRejectedValue() + "]";
                } else {
                    msg = "参数错误[" + ferr.getField() + "=" + ferr.getRejectedValue() + "]," + ferr.getDefaultMessage();
                }
                respResult.setCode(HttpStatus.BAD_REQUEST.value(), msg);
                logger.warn(msg);
                return respResult;
            }
        }
        return respResult;
    }
}
