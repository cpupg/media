package com.sheepfly.media.repository;

import com.sheepfly.media.entity.ResourceAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源-专辑关联仓库。
 *
 * @author 东方红。
 */
public interface ResourceAlbumRepository
        extends JpaRepository<ResourceAlbum, String>, JpaSpecificationExecutor<ResourceAlbum> {
}
