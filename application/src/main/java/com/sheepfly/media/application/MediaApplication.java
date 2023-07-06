package com.sheepfly.media.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sheepfly.media")
@ImportResource("classpath:configs/springboot.xml")
@EnableJpaRepositories(basePackages = "com.sheepfly.media.dataaccess.repository")
@MapperScan("com.sheepfly.media.dataaccess.dao")
public class MediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaApplication.class, args);
    }

}
