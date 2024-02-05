package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;
import com.sheepfly.media.service.base.AlbumService;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl extends BaseJpaServiceImpl<Album, String, AlbumRepository> implements AlbumService {
}
