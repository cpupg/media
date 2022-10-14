package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.service.ISiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Resource(name = "siteRepository")
    private SiteRepository repository;

}
