package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.repository.AlbumResourceRepository;
import com.sheepfly.media.service.base.AlbumResourceService;
import org.springframework.stereotype.Service;

@Service
public class AlbumResourceServiceImpl extends BaseJpaServiceImpl<AlbumResource, String, AlbumResourceRepository>
        implements AlbumResourceService {
}
