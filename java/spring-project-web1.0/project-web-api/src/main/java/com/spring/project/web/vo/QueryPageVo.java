package com.spring.project.web.vo;

import com.spring.project.web.api.validator.QueryKey;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.text.ParseException;
import java.util.Date;

public class QueryPageVo {
    /**
     * 分页查询起页
     */
    @NotNull(message = "起始页不允许为空")
    @Min(value = 0)
    private Integer start = 0;

    /**
     * 分页查询止页
     */
    @Min(value = 0, message = "start不允许小于0")
    @Max(value = 1000, message = "length不允许超过1000")
    private Integer length = 20;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "开始时间不允超当前时间")
    private Date beginDate;

    /**
     * 截止时间
     * 开始时间不能大于结束时 结束时间为null 例外
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "结束时间不允超当前时间")
    private Date endDate;

    /**
     * 获取开始页
     *
     * @return 开始页
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置开始页
     *
     * @param start 开始页
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 设置分页长度
     *
     * @return 分页长度
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置分页长度
     *
     * @param length 分页长度
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取开始时间
     *
     * @return 开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始时间
     *
     * @param beginDate 开始时间
     */
    public void setBeginDate(Date beginDate) throws ParseException {
        this.beginDate = beginDate;
    }

    /**
     * 获取截止时间
     *
     * @return 截止时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置截止时间
     *
     * @param endDate 截止时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

