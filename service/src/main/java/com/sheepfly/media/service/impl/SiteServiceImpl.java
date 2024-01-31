package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sheepfly.media.dataaccess.mapper.SiteMapper;
import com.sheepfly.media.dataaccess.entity.Site;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.data.SiteData;
import com.sheepfly.media.common.form.param.SiteParam;
import com.sheepfly.media.dataaccess.repository.AuthorRepository;
import com.sheepfly.media.dataaccess.repository.SiteRepository;
import com.sheepfly.media.service.base.ISiteService;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 站点 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class SiteServiceImpl extends BaseJpaServiceImpl<Site, String, SiteRepository> implements ISiteService {
    private static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);
    @Resource(name = "siteRepository")
    private SiteRepository repository;
    @Resource(name = "authorRepository")
    private AuthorRepository authorRepository;
    @Resource(name = "siteMapper")
    private SiteMapper mapper;

    @Override
    public ProTableObject<Site> querySiteList(ProComponentsRequestVo<Object, SiteParam, Object> vo) {
        SiteParam form = vo.getParams();
        Page<Object> page = PageHelper.startPage(form.getCurrent(), form.getPageSize());
        List<Site> siteList = mapper.querySiteList(form);
        return ProTableObject.success(siteList, page.getTotal());
    }

    @Override
    public boolean canSiteBeDelete(String siteId) throws BusinessException {
        return authorRepository.countBySiteId(siteId) == 0;
    }

    @Override
    public boolean validateSiteData(SiteData siteData) throws BusinessException {
        if (StringUtils.isEmpty(siteData.getSiteName()) || StringUtils.isEmpty(siteData.getUrl())) {
            throw new BusinessException(ErrorCode.SITE_NAME_URL_CANT_BE_NULL);
        }
        String url = siteData.getUrl();
        if (url.indexOf("http://") != 0 && url.indexOf("https://") != 0) {
            throw new BusinessException(ErrorCode.URL_IS_ILLEGAL);
        }
        return false;
    }
}
