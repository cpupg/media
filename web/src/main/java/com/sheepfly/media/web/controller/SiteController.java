package com.sheepfly.media.web.controller;


import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.SiteData;
import com.sheepfly.media.common.form.filter.SiteFilter;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.dataaccess.entity.Site;
import com.sheepfly.media.service.base.ISiteService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 站点 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@RestController
@RequestMapping("/site")
public class SiteController {
    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Resource(name = "siteServiceImpl")
    private ISiteService service;
    @Resource(name = "defaultValidator")
    private Validator validator;

    /**
     * 查询系统中的站点。
     *
     * @param vo 查询参数
     *
     * @return 和输入参数中的条件匹配的站点。
     */
    @PostMapping("/querySiteList")
    public ProTableObject<Site> querySiteList(@RequestBody ProComponentsRequestVo<Object, SiteFilter, Object> vo) {
        SiteFilter form = vo.getParams();
        if (form.getSiteName() != null && !StringUtils.isBlank(form.getSiteName())) {
            // todo spring security
            String siteName = form.getSiteName().replace(Constant.SQL_LIKE, Constant.BLANK_STRING);
            form.setSiteName(Constant.SQL_LIKE + siteName + Constant.SQL_LIKE);
        }
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
    public ResponseData<Site> addSite(@RequestBody @Validated SiteData siteData)
            throws InvocationTargetException, IllegalAccessException {
        Site site = new Site();
        BeanUtils.copyProperties(site, siteData);
        site.setCreateTime(new Date());
        Set<ConstraintViolation<Site>> validate = validator.validate(site);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException("验证失败", validate);
        }
        Site savedSite = service.save(site);
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
    public ResponseData<Object> deleteSite(@RequestParam("id") String siteId) throws BusinessException {
        if (StringUtils.isEmpty(siteId)) {
            throw new BusinessException(ErrorCode.SITE_ID_CANT_NULL);
        }
        if (service.canSiteBeDelete(siteId)) {
            Site site = service.safeLogicDeleteById(siteId, Site.class, ErrorCode.DELETE_NOT_EXIST_DATA);
            return ResponseData.success(site);
        } else {
            return ResponseData.fail(ErrorCode.SITE_CANT_BE_DELETE);
        }
    }
}

