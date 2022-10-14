package com.sheepfly.media.service;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.repository.SiteRepository;

/**
 * <p>
 * 站点 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface ISiteService extends BaseJpaService<Site, String, SiteRepository> {
}
