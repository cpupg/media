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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 查询系统中的站点。
     *
     * @param vo 查询参数
     *
     * @return 和输入参数中的条件匹配的站点。
     *
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException 异常
     */
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

    /**
     * 录入新站点。
     *
     * <p>新站点的id由后台生成，创建时间由前台传入。</p>
     *
     * @param site 要录入的站点
     *
     * @return 录入结果。
     */
    @PostMapping("/addSite")
    @ResponseBody
    public DataObject<Site> addSite(@RequestBody Site site) {
        site.setCreateTime(LocalDate.now());
        boolean save = iSiteService.save(site);
        if (save) {
            return DataObject.success("添加成功");
        } else {
            return DataObject.fail("添加失败");
        }
    }

    /**
     * 删除站点。
     *
     * @param siteId 要删除的站点id。
     *
     * @return 操作结果。
     */
    @GetMapping("/delete")
    @ResponseBody
    public DataObject<Object> deleteSite(@RequestParam("id") long siteId) {
        Site site = iSiteService.getById(siteId);
        if (site == null) {
            return DataObject.fail("删除失败，记录不存在1");
        }
        boolean result = iSiteService.removeById(siteId);
        if (result) {
            return DataObject.success("删除成功");
        } else {
            return DataObject.fail("删除失败");
        }
    }
}

