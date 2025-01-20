package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.Collect;
import com.sheepfly.media.dataaccess.repository.CollectRepository;
import com.sheepfly.media.service.base.CollectService;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl
        extends BaseJpaServiceImpl<Collect, String, CollectRepository>
        implements CollectService {
}
