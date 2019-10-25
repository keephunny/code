/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-12 10:42
 */
@Component
@ConfigurationProperties(prefix = "filter.xss")
public class FilterXssConfigEntity {

    /**
     * xssfilter需要过滤的地址
     */
    private List<String> paths;

    /**
     * xssfilter 白名单
     */
    private List<String> ignores;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public List<String> getIgnores() {
        return ignores;
    }

    public void setIgnores(List<String> ignores) {
        this.ignores = ignores;
    }
}
