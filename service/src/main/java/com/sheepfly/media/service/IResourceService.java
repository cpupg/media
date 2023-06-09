package com.sheepfly.media.service;

import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.filter.ResourceFilter;
import com.sheepfly.media.http.ProTableObject;
import com.sheepfly.media.repository.ResourceRepository;
import com.sheepfly.media.http.ProComponentsRequestVo;
import com.sheepfly.media.vo.ResourceVo;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IResourceService extends BaseJpaService<Resource, String, ResourceRepository> {
    /**
     * 查询资源。
     *
     * @param form 查询表单。
     *
     * @return 满足条件的资源。
     */
    ProTableObject<ResourceVo> queryResourceVoList(ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form);
}
