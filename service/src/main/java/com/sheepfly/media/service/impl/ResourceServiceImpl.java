package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sheepfly.media.dao.ResourceMapper;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.filter.ResourceFilter;
import com.sheepfly.media.http.ProComponentsRequestVo;
import com.sheepfly.media.http.ProTableObject;
import com.sheepfly.media.repository.ResourceRepository;
import com.sheepfly.media.service.IResourceService;
import com.sheepfly.media.vo.ResourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class ResourceServiceImpl extends BaseJpaServiceImpl<Resource, String, ResourceRepository>
        implements IResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ProTableObject<ResourceVo> queryResourceVoList(
            ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form) {
        ResourceFilter params = form.getParams();
        Page<Object> page = PageHelper.startPage(params.getCurrent(), params.getPageSize());
        List<ResourceVo> list = resourceMapper.selectResourceVoList(form);
        return ProTableObject.success(list, (int) page.getTotal());
    }
}
