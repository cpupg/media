package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.querylist.SiteForm;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 站点 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Controller
@RequestMapping("/site")
public class SiteController {
    @PostMapping("/fetchSiteVoListPro")
    @ResponseBody
    public ProTableObject<Site> querySiteList(@RequestBody ProComponentsRequestVo<Object, SiteForm, Object> vo) {
        List<Site> siteList = new ArrayList<>();
        ProTableObject<Site> proTableObject = new ProTableObject<>();
        proTableObject.setTotal(0);
        proTableObject.setData(siteList);
        return proTableObject;
    }
}

