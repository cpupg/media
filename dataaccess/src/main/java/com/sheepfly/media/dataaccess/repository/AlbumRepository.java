package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumRepository extends JpaRepository<Album, String>, JpaSpecificationExecutor<Album> {
}
