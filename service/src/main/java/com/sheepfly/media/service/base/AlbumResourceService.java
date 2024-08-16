package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.repository.AlbumResourceRepository;

/**
 * 专辑资源服务。
 *
 * <p>专辑相关操作放到AlbumService或按照业务加在其他service里，此文件不再新增方法。</p>
 *
 * @author chen
 */
public interface AlbumResourceService extends BaseJpaService<AlbumResource, String, AlbumResourceRepository> {
    // 和专辑有关的内容写在AlbumService中。
}
