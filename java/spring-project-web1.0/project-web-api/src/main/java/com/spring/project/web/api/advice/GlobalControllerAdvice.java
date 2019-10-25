/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.advice;

import com.spring.project.web.vo.RespResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获，避免异常信息抛到前端。
 * 可以保证接口的稳定，即使有异常，接口的数据格式也能正常输入，保证前端正常处理
 * 异常状态码，采用了http状态码 200：成功 400：客户端错 500：服务端错误
 *
 * @author w
 * 创建时间 2019-07-11 14:12
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    /**
     * 异常信息是否抛出
     */
    @Value("${controller.log.err}")
    private Boolean LogErrorIsShow;
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    //TODO增加404错误

    /**
     * 异常捕获 服务器端异常
     *
     * @param request  request
     * @param response response
     * @param e        Exception
     * @return 返回值
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespResult handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "系统异常";
        return returResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public RespResult handleIllegalStateException(HttpServletRequest request, IllegalStateException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "参数错误1";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RespResult handleBindException(HttpServletRequest request, BindException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "参数错误2";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RespResult handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "http请求类型错误";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseBody
    public RespResult handleConversionFailedException(HttpServletRequest request, ConversionFailedException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "参数错误4";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public RespResult handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        //Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'; nested exception is java.lang.NumberFormatException: For input string: \"1p\""
        String msg = "参数格式错误5";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public RespResult handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "参数缺少";
        return returResult(HttpStatus.BAD_REQUEST.value(), msg, e.getMessage());
    }



    /**
     * 异常捕获 404资源不存在
     *
     * @param request request
     * @param e       AuthenticationException
     * @return 返回值
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public RespResult handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage(), e);
        String msg = "请求地址不存在";
        return returResult(HttpStatus.NOT_FOUND.value(), msg, e.getMessage());
    }

    /**
     * 包装RespResult输出
     *
     * @param code         状态码
     * @param msg          业务信息
     * @param exceptionMsg 异常信息
     * @return
     */
    private RespResult returResult(int code, String msg, String exceptionMsg) {
        if (LogErrorIsShow) {
            msg += "," + exceptionMsg;
        }
        RespResult responseVo = new RespResult(code, msg);
        return responseVo;
    }


}