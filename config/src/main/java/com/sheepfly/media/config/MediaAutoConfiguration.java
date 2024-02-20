package com.sheepfly.media.config;

import com.sheepfly.media.config.bean.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

/**
 * 应用自动配置。
 */
@Configuration
@Slf4j
public class MediaAutoConfiguration {
    /**
     * 自动从配置文件中获取系统版本号。
     *
     * <p>配置文件由工作流自动生成，因此不会出现在代码仓库中。如果没有此文件，则给出默认版本号1.0.0。</p>
     */
    @Bean
    public Version versionConfig() {
        Version version = new Version();
        ClassPathResource cpr = new ClassPathResource("version.properties");
        try {
            InputStream inputStream = cpr.getInputStream();
            version.load(inputStream);
        } catch (Exception e) {
            log.warn("加载版本号发生异常，使用默认版本号1.0.0。异常原因：{}", e.getMessage());
        }
        return version;
    }
}
