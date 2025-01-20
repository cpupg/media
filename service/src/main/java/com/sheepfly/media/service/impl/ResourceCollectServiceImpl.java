package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import com.sheepfly.media.dataaccess.repository.ResourceCollectRepository;
import com.sheepfly.media.service.base.ResourceCollectService;
import org.springframework.stereotype.Service;

@Service
public class ResourceCollectServiceImpl
        extends BaseJpaServiceImpl<ResourceCollect, String, ResourceCollectRepository>
        implements ResourceCollectService {
}
