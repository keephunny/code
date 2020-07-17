/**
 * open source software quality management tool.
 * Copyright (C) 2008-2019
 * mailto:contact
 */
package com.spring.project.web.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerShow)
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.spring.project.web.api.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                // 隐藏UI上的Models模块
                .defaultModelsExpandDepth(-1)
                .defaultModelExpandDepth(0)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("xx", "", "");
        return new ApiInfoBuilder()
                .title("系统测试API接口")
                .description("API接口的描述")//文档接口的描述
                .contact(contact)
                .version("1.1.0")//版本号
                .build();
    }

//    @Bean
//    public Docket createDocket() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder().title("系统接口API文档")
//                        .description("系统接口API文档")
//                        .version("1.0").build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.spring.project.web.api.controller"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }
}
