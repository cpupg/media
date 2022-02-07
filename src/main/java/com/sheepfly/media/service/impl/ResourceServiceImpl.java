package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.dao.ResourceMapper;
import com.sheepfly.media.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
