package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.ResourceType;
import com.sheepfly.media.repository.ResourceTypeRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源和类型关联 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class ResourceTypeServiceImpl extends BaseJpaServiceImpl<ResourceType, String, ResourceTypeRepository> {

}
