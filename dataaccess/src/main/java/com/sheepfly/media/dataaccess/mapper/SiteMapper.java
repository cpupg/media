package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.entity.Site;
import com.sheepfly.media.common.form.filter.SiteFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 站点 Mapper 接口
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Mapper
public interface SiteMapper {
    /**
     * 根据条件查询网站清单。
     *
     * @param siteFilter 查询条件。
     *
     * @return 查询结果。
     */
    List<Site> querySiteList(SiteFilter siteFilter);

    /**
     * 查询网站数量。
     *
     * @param siteFilter 查询条件。
     *
     * @return 数量。
     */
    int countSiteList(SiteFilter siteFilter);
}
