package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.ResourceType;
import com.sheepfly.media.dao.ResourceTypeMapper;
import com.sheepfly.media.service.IResourceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceType> implements IResourceTypeService {

}
