package com.sheepfly.media.config;

import com.sheepfly.media.config.bean.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        ClassPathResource cpr = new ClassPathResource("media-application.properties");
        try {
            InputStream inputStream = cpr.getInputStream();
            Properties properties = new Properties();
            properties.load(inputStream);
            version.setMainVersion(properties.getProperty("main"));
            if (StringUtils.isBlank(version.getMainVersion())) {
                log.warn("已设置主版本号，但主版本号为空");
                throw new Exception("已设置主版本号，但主版本号为空");
            }
        } catch (Exception e) {
            log.warn("设置主版本号发生异常，使用默认版本号1.0.0。异常原因：{}", e.getMessage());
            version.setMainVersion("v1.0.0");
        }
        return version;
    }
}
