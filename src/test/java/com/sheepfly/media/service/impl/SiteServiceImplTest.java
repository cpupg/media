package com.sheepfly.media.service.impl;

import com.sheepfly.media.MediaApplication;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.service.ISiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = MediaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Profile("dev")
public class SiteServiceImplTest {
    @Resource(name = "siteServiceImpl")
    private ISiteService service;

    @Test
    public void testFindById() throws Exception {
        Site site = new Site();
        site.setSiteName("百度");
        site.setCreateTime(new Date());
        site.setUrl("http1://www.baidu.com");
        try {
            Site save = service.save(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogicById() {
        boolean b = service.logicExistById("12345");
        System.out.println(b);
    }
}