package com.sheepfly.media.service;

import com.sheepfly.media.entity.Site;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.data.SiteData;
import com.sheepfly.media.form.filter.SiteFilter;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.http.ProComponentsRequestVo;
import com.sheepfly.media.http.ProTableObject;

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

    /**
     * 验证网站是否可以被删除。
     *
     * <p>只有同时满足以下条件时，一个网站可以被删除：</p>
     * <ul>
     * <li>该网站下的作者已经全部删除。</li>
     * <li>该网站下的资源已经全部删除。</li>
     * <li>该网站下的专辑已经全部删除。</li>
     * </ul>
     *
     * <p>由于作者、专辑和网站三者之间的关联关系，只要一个网站下没有作者，则说明此网站下同时没有
     * 专辑和资源。</p>
     *
     * @param siteId 网站标识。
     *
     * @return 可以删除返回true，否则抛出异常。
     *
     * @throws BusinessException 不能删除的原因。
     */
    boolean canSiteBeDelete(String siteId) throws BusinessException;
}
