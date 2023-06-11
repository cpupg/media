package com.sheepfly.media.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("media.version")
@Configuration
@Setter
@Getter
public class VersionProperty {
    private String main;
    private String backVersion;
    private String frontVersion;
}
