package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.dataaccess.vo.ResourceVo;

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
