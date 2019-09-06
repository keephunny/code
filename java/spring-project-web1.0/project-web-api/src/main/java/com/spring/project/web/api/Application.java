/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * webapi接口主程序入口
 *
 * @author 汪强
 * 创建时间 2019-09-04 11:29
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class}, scanBasePackages = {"com.spring.project.web.api", "com.spring.project.web.core"})
@MapperScan("com.spring.project.web.core.mapper")
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * webapi接口主程序入口
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        logger.info("程序启动");
        SpringApplication.run(Application.class);
    }


    /**
     * 格式化fastjosn输出格式
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //TODO null 处理
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
//                SerializerFeature.BeanToArray.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullNumberAsZero,
//                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue
//                SerializerFeature.DisableCheckSpecialChar
        );
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConvert.setSupportedMediaTypes(fastMediaTypes);

        fastConvert.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConvert);
    }
}
