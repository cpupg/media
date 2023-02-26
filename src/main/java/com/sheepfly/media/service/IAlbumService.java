package com.sheepfly.media.service;

import com.sheepfly.media.entity.Album;
import com.sheepfly.media.repository.AlbumRepository;

/**
 * <p>
 * 专辑 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IAlbumService extends BaseJpaService<Album, String, AlbumRepository> {

}
