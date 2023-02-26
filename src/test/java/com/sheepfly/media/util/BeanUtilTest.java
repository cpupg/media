package com.sheepfly.media.util;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.data.SiteData;
import org.junit.Test;

import java.time.LocalDate;

public class BeanUtilTest {

    @Test
    public void dataToEntity() {
        SiteData siteData = new SiteData();
        siteData.setId("12345");
        siteData.setSiteName("测试工具类");
        siteData.setUrl("beanUtils");
        siteData.setCreateTime(LocalDate.now());
        siteData.setUpdateTime(LocalDate.now());
        Site site = BeanUtil.dataToEntity(siteData, new Site());
        System.out.println(site);
    }
}