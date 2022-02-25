package com.sheepfly.media.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        File file = new File("datasource.properties");
        if (file.exists()) {
            log.info("使用数据源配置:" + file.getAbsolutePath());
            try {
                properties.load(new FileInputStream("datasource.properties"));
                dataSource.setDriverClassName(properties.getProperty("driverClassName"));
                dataSource.setUrl(properties.getProperty("url"));
                dataSource.setUsername(properties.getProperty("username"));
                dataSource.setPassword(properties.getProperty("password"));
            } catch (IOException e) {
                log.error("加载数据库配置文件失败", e);
            }
        } else {
            // 打包后启动时需要传入
            log.info("找不到配置文件从系统属性获取数据源配置");
            dataSource.setDriverClassName(System.getProperty("driverClassName"));
            dataSource.setUrl(System.getProperty("url"));
            dataSource.setUsername(System.getProperty("username"));
            dataSource.setPassword(System.getProperty("password"));
        }
        return dataSource;
    }
}
