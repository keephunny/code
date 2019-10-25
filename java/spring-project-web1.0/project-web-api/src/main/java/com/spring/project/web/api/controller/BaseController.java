/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.controller;


import com.spring.project.web.utils.lang.DateTimeUtils;
import com.spring.project.web.vo.RespResult;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-06 11:37
 */
public class BaseController {
    /**
     * 异常信息是否抛出
     */
    @Value("${controller.log.err}")
    private Boolean LogErrorIsShow;
    protected final int SQL_IN_NUM = 300;
    protected final int BAD_REQUEST_CODE = 400;
    /**
     * 日志
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * dozerBeanMapper对象转换
     */
    @Autowired
    protected DozerBeanMapper dozerBeanMapper;

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
                //是否绑定成功
                if (ferr.isBindingFailure()) {
                    msg = "参数错误," + ferr.getField() + "=" + ferr.getRejectedValue() + "";
                    if (LogErrorIsShow) {
                        msg += "," + ferr.getDefaultMessage();
                    }
                } else {
                    msg = "参数错误," + ferr.getField() + "=" + ferr.getRejectedValue() + "," + ferr.getDefaultMessage();
                }
//                if ("typeMismatch".equals(ferr.getCode())) {
//                    msg = "参数错误[" + ferr.getField() + "=" + ferr.getRejectedValue() + "]";
//                } else {
//                    msg = "参数错误[" + ferr.getField() + "=" + ferr.getRejectedValue() + "]," + ferr.getDefaultMessage();
//                }
                respResult.setCode(BAD_REQUEST_CODE, msg);
                logger.warn(msg);
                return respResult;
            }
        }
        return respResult;
    }

    protected RespResult checkBeginEndDate(Date beginDate, Date endDate) {
        RespResult respResult = new RespResult();
        if (beginDate == null || endDate == null) {
            return respResult;
        }
        long n = DateTimeUtils.diffSeconds(beginDate, endDate);
        if (n == 0) {
            respResult.setCode(BAD_REQUEST_CODE, "结束时时不能等于开始时间");
        } else if (n < 0) {
            respResult.setCode(BAD_REQUEST_CODE, "结束时时不能小于开始时间");
        }
        return respResult;
    }

    protected RespResult checkBeginEndDateDiff(Date beginDate, Date endDate, String diffType, int n) {
        RespResult respResult = checkBeginEndDate(beginDate, endDate);
        if (respResult.getCode() != HttpStatus.OK.value()) {
            return respResult;
        }
        if (beginDate == null || endDate == null) {
            return respResult;
        }
        long diffN = 0;
        String msg = "";
        switch (diffType) {
            case "year": {
                diffN = DateTimeUtils.diffYear(beginDate, endDate);
                //如果年范围没超过，则还要验证月 日 时 分 秒
                msg = "年";
                break;
            }
            case "month": {
                diffN = DateTimeUtils.diffMonth(beginDate, endDate);
                msg = "月";
                break;
            }
            case "week": {
                diffN = DateTimeUtils.diffWeek(beginDate, endDate);
                msg = "周";
                break;
            }
            case "day": {
                diffN = DateTimeUtils.diffDay(beginDate, endDate);
                msg = "天";
                break;
            }
            case "hour": {
                diffN = DateTimeUtils.diffHour(beginDate, endDate);
                msg = "小时";
                break;
            }
            case "minute": {
                diffN = DateTimeUtils.diffMinute(beginDate, endDate);
                msg = "分钟";
                break;
            }
            case "seconds": {
                diffN = DateTimeUtils.diffSeconds(beginDate, endDate);
                msg = "秒";
                break;
            }
        }
        if (diffN > n) {
            respResult.setCode(BAD_REQUEST_CODE, "时间跨度不允许超过" + n + msg);
        }
        return respResult;
    }


    /**
     * 检测id参数
     *
     * @param id id
     * @return 操作结果
     */
    protected RespResult checkID(Integer id) {
        RespResult respResult = new RespResult();
        if (id == null || id <= 0) {
            respResult.setCode(BAD_REQUEST_CODE, "参数错误,id不能为空且大于零");
        }
        return respResult;
    }


    /**
     * 检测ids参数
     *
     * @param id id
     * @return 操作结果
     */
    protected RespResult checkIDs(Integer[] id) {
        RespResult respResult = new RespResult();
        if (id == null || id.length <= 0) {
            respResult.setCode(BAD_REQUEST_CODE, "参数错误,id不能为空");
            return respResult;
        }
        if (id.length > SQL_IN_NUM) {
            respResult.setCode(BAD_REQUEST_CODE, "参数错误,数量不允于同时超过" + SQL_IN_NUM);
        }
        for (int i = 0; i < id.length; i++) {
            for (int j = i + 1; j < id.length; j++) {
                if (id[i] == id[j]) {
                    respResult.setCode(BAD_REQUEST_CODE, "参数错误,id=" + id[i] + "有重复");
                    return respResult;
                }
            }
        }
        return respResult;
    }
}
