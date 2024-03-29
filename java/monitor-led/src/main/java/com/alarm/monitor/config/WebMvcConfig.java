/*
 * Copyright (c) 2019.
 */
package com.alarm.monitor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-04 15:17
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * swagger静态资源配置
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("*.js").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
