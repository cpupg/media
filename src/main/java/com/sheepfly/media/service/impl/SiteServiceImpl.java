package com.sheepfly.media.service.impl;

import com.github.pagehelper.PageHelper;
import com.sheepfly.media.dao.SiteMapper;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.data.SiteData;
import com.sheepfly.media.form.filter.SiteFilter;
import com.sheepfly.media.repository.AuthorRepository;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.service.ISiteService;
import com.sheepfly.media.util.ValidateUtil;
import com.sheepfly.media.vo.common.ErrorCode;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;
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
    public ProTableObject<Site> querySiteList(ProComponentsRequestVo<Object, SiteFilter, Object> vo) {
        SiteFilter form = vo.getParams();
        PageHelper.startPage(form.getCurrent(), form.getPageSize());
        List<Site> siteList = mapper.querySiteList(form);
        int count = mapper.countSiteList(form);
        return ProTableObject.success(siteList, count);
    }

    @Override
    public boolean canSiteBeDelete(String siteId) throws BusinessException {
        return authorRepository.countBySiteId(siteId) == 0;
    }

    @Override
    public boolean validateSiteData(SiteData siteData) throws BusinessException {
        if (ValidateUtil.isEmptyString(siteData.getSiteName()) || ValidateUtil.isEmptyString(siteData.getUrl())) {
            throw new BusinessException(ErrorCode.SITE_NAME_URL_CANT_BE_NULL);
        }
        String url = siteData.getUrl();
        if (url.indexOf("http://") != 0 && url.indexOf("https://") != 0) {
            throw new BusinessException(ErrorCode.URL_IS_ILLEGAL);
        }
        return false;
    }
}
