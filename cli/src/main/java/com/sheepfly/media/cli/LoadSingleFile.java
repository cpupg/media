package com.sheepfly.media.cli;

import com.sheepfly.media.cli.task.impl.LoadSingleFileTaskImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sheepfly.media")
@EnableJpaRepositories(basePackages = "com.sheepfly.media.dataaccess.repository")
@MapperScan("com.sheepfly.media.dataaccess.mapper")
@ImportResource("classpath:configs/springboot.xml")
public class LoadSingleFile {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(LoadSingleFile.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = application.run(args);
        LoadSingleFileTaskImpl task = context.getBean(LoadSingleFileTaskImpl.class);
        task.executeTask();
    }
}
