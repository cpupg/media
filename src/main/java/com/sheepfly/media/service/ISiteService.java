package com.sheepfly.media.service;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.data.SiteData;
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
     * 校验输入的网站数据。
     *
     * <p>校验以下内容：</p>
     * <ul>
     * <li>网站名称和网站地址不能为空。</li>
     * <li>地址必须以http://或者https://开头。</li>
     * </ul>
     *
     * @param siteData 网站数据。
     *
     * @return 验证通过返回true。
     *
     * @throws BusinessException 验证不通过的原因。
     */
    boolean validateSiteData(SiteData siteData) throws BusinessException;

    /**
     * 查询网站。
     *
     * @param vo 查询参数。
     *
     * @return 查询结果。
     */
    ProTableObject<Site> querySiteList(ProComponentsRequestVo<Object, SiteFilter, Object> vo);
}
