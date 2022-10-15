package com.sheepfly.media.service.impl;

import com.github.pagehelper.PageHelper;
import com.sheepfly.media.dao.SiteMapper;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.filter.SiteFilter;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.service.ISiteService;
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
}
