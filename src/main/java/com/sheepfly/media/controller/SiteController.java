package com.sheepfly.media.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.querylist.SiteForm;
import com.sheepfly.media.service.ISiteService;
import com.sheepfly.media.util.FormUtil;
import com.sheepfly.media.vo.common.DataObject;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
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
    @Resource(name = "siteServiceImpl")
    private ISiteService iSiteService;

    @PostMapping("/fetchSiteVoListPro")
    @ResponseBody
    public ProTableObject<Site> querySiteList(@RequestBody ProComponentsRequestVo<Object, SiteForm, Object> vo)
            throws InvocationTargetException, IllegalAccessException {
        QueryWrapper<Site> queryWrapper = new QueryWrapper<>();
        SiteForm siteForm = vo.getParams();
        PageHelper.startPage(siteForm.getCurrent(), siteForm.getPageSize());
        List<Site> list = iSiteService.list(FormUtil.formToWrapper(vo.getParams()));
        int count = iSiteService.count(queryWrapper);
        ProTableObject proTableObject = new ProTableObject(count, list);
        return proTableObject;
    }

    @PostMapping("/addSite")
    @ResponseBody
    public DataObject<Site> addSite(@RequestBody Site site) {
        // todo 生成id
        site.setCreateTime(LocalDate.now());
        boolean save = iSiteService.save(site);
        if (save) {
            return DataObject.success(iSiteService.list());
        } else {
            return DataObject.fail("添加失败");
        }
    }
}

