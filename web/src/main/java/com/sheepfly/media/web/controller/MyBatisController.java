package com.sheepfly.media.web.controller;

import com.chen.mybatisreload.core.MyBatisReloadService;
import com.chen.mybatisreload.core.bean.ReloadContext;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class MyBatisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisController.class);
    @Resource
    private MyBatisReloadService myBatisService;
    @Resource
    private ApplicationContext applicationContext;


    @PostMapping(value = "/reloadMapper", consumes = "application/json")
    public ReloadContext reloadMapper(@RequestBody ReloadContext reloadCopntext) {
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        if (ArrayUtils.contains(activeProfiles, "prd")) {
            LOGGER.warn("生产环境无法使用此功能");
            return reloadCopntext;
        }
        return myBatisService.reloadMapper(reloadCopntext);
    }
}
