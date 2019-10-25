/**
 * gsafety, open source software quality management tool.
 * Copyright (C) 2008-2019 gsafety
 * mailto:contact
 */
package com.spring.project.web.api.config;

import com.spring.project.web.api.filter.ManagerFilter;
import com.spring.project.web.api.filter.XSSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * XssFilterConfig
 *
 * @author 汪强
 * 创建时间 2019-08-15 16:47
 */
@Configuration
public class FilterConfig {
    @Autowired
    private FilterXssConfigEntity filterXssConfigEntity;

    /**
     * xss过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //TODO 添加白名单配置 不过滤静态资源
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(xssFilter());
        //1.去除静态资源
        //2.去除指定白名单
        for (String path : filterXssConfigEntity.getPaths()) {
            bean.addUrlPatterns(path.trim());
        }
//        bean.addUrlPatterns("/*");
        bean.setName("xssFilter");
        return bean;
    }

    @Bean
    public FilterRegistrationBean managerFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(managerFilter());
        bean.addUrlPatterns("/actuator/*");
        bean.setName("managerFilter");
        return bean;
    }

    @Bean(name = "xssFilter")
    public Filter xssFilter() {
        return new XSSFilter();
    }

    @Bean(name = "managerFilter")
    public Filter managerFilter() {
        return new ManagerFilter();
    }
}
