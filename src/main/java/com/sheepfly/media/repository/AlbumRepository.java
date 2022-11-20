package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 专辑仓库。
 *
 * @author wrote-code
 */
public interface AlbumRepository extends JpaRepository<Album, String>, JpaSpecificationExecutor<Album> {
}
