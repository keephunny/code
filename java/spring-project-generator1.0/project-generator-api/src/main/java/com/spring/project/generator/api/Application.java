package com.spring.project.generator.api;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class}, scanBasePackages = {"com.spring.project.generator.api", "com.spring.project.generator.core"})
@MapperScan("com.spring.project.generator.core.mapper")
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

}
