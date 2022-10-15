package com.sheepfly.media.service;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.form.filter.SiteFilter;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;

/**
 * <p>
 * 站点 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface ISiteService extends BaseJpaService<Site, String, SiteRepository> {
    /**
     * 查询网站。
     *
     * @param vo 查询参数。
     *
     * @return 查询结果。
     */
    ProTableObject<Site> querySiteList(ProComponentsRequestVo<Object, SiteFilter, Object> vo);
}
