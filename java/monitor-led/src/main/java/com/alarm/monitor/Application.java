package com.alarm.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class}, scanBasePackages = {"com.alarm.monitor"})
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        logger.info("程序启动");
        SpringApplication.run(Application.class);

    }
}
