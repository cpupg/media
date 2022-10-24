package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Site;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.data.SiteData;
import com.sheepfly.media.form.filter.SiteFilter;
import com.sheepfly.media.service.ISiteService;
import com.sheepfly.media.util.BeanUtil;
import com.sheepfly.media.util.ValidateUtil;
import com.sheepfly.media.vo.common.ErrorCode;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;
import com.sheepfly.media.vo.common.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    private ISiteService service;

    /**
     * 查询系统中的站点。
     *
     * @param vo 查询参数
     *
     * @return 和输入参数中的条件匹配的站点。
     */
    @PostMapping("/fetchSiteVoListPro")
    @ResponseBody
    public ProTableObject<Site> querySiteList(@RequestBody ProComponentsRequestVo<Object, SiteFilter, Object> vo) {
        return service.querySiteList(vo);
    }

    /**
     * 录入新站点。
     *
     * <p>新站点的id由后台生成，创建时间由前台传入。</p>
     *
     * @param siteData 要录入的站点
     *
     * @return 录入结果。
     */
    @PostMapping("/addSite")
    @ResponseBody
    public ResponseData<Site> addSite(@RequestBody SiteData siteData) throws BusinessException {
        siteData.setId(null);
        service.validateSiteData(siteData);
        Site savedSite = service.save(BeanUtil.dataToEntity(siteData, new Site()));
        return ResponseData.success(savedSite);
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
    public ResponseData<Object> deleteSite(@RequestParam("id") String siteId) throws BusinessException {
        if (ValidateUtil.isEmptyString(siteId)) {
            throw new BusinessException(ErrorCode.SITE_ID_CANT_NULL);
        }
        if (!service.existsById(siteId)) {
            return ResponseData.fail(ErrorCode.DELETE_NOT_EXIST_DATA);
        } else {
            service.deleteById(siteId);
            return ResponseData.success();
        }
    }
}

