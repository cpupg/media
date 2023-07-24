package com.sheepfly.media.config;

import com.sheepfly.media.config.bean.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 应用自动配置。
 */
@Configuration
public class MediaAutoConfiguration {
    /**
     * 自动从配置文件中获取系统版本号。
     *
     * <p>配置文件由工作流自动生成，因此不会出现在代码仓库中。如果没有此文件，则给出默认版本号1.0.0。</p>
     */
    @Bean
    @Lazy
    public Version versionConfig() {
        Version version = new Version();
        ClassPathResource cpr = new ClassPathResource("version.properties");
        try {
            InputStream inputStream = cpr.getInputStream();
            Properties properties = new Properties();
            properties.load(inputStream);
            version.setMainVersion(properties.getProperty("main"));
        } catch (IOException e) {
            // throw new RuntimeException(e);
            // 不需要抛出异常
            version.setMainVersion("1.0.0");
        }
        return version;
    }
}
