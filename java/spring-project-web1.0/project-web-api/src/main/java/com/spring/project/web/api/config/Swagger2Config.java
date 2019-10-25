/**
 *  gsafety, open source software quality management tool.
 *  Copyright (C) 2008-2019 gsafety
 *  mailto:contact
 */
package com.spring.project.web.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("系统接口API文档")
                        .description("系统接口API文档")
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.project.web.api.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
