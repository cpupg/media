package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;

public interface AlbumService extends BaseJpaService<Album, String, AlbumRepository> {
}
