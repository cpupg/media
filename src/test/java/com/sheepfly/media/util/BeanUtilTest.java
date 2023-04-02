package com.sheepfly.media.util;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.data.SiteData;
import org.junit.Test;

import java.util.Date;

public class BeanUtilTest {

    @Test
    public void dataToEntity() {
        SiteData siteData = new SiteData();
        siteData.setId("12345");
        siteData.setSiteName("测试工具类");
        siteData.setUrl("beanUtils");
        siteData.setCreateTime(new Date());
        siteData.setUpdateTime(new Date());
        Site site = BeanUtil.dataToEntity(siteData, new Site());
        System.out.println(site);
    }
}