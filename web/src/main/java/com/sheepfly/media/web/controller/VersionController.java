package com.sheepfly.media.web.controller;

import com.sheepfly.media.config.bean.Version;
import com.sheepfly.media.web.config.VersionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {
    @Autowired
    private VersionProperty versionProperty;
    @Autowired
    private Version version;

    @GetMapping("/main")
    public String getMain() {
        return version.getMainVersion();
    }

    @GetMapping("/backVersion")
    public String getBackVersion() {
        return versionProperty.getBackVersion();
    }

    @GetMapping("/frontVersion")
    public String getFrontVersion() {
        return versionProperty.getFrontVersion();
    }
}
