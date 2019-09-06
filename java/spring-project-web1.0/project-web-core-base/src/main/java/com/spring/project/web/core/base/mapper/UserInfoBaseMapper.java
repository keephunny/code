/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.core.base.mapper;

import com.spring.project.web.core.base.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserInfoMapper
 *
 * @author 汪强
 * 创建时间 2019-09-05 16:41
 */
@Repository
public interface UserInfoBaseMapper extends BaseMapper<UserInfo> {
    /**
     * 分页查询统计总行数
     *
     * @param objQueryPage 查询参数
     * @return 查询结果
     */
    long queryCount(Object objQueryPage);

    /**
     * 分页查询
     *
     * @param objQueryPage 查询参数
     * @return 查询结果
     */
    List<UserInfo> queryPage(Object objQueryPage);
}
