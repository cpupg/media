package com.sheepfly.media.cli;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.sheepfly.media", exclude = JpaRepositoriesAutoConfiguration.class)
@ImportResource("classpath:configs/springboot.xml")
public class CliTestConfiguration {
}
