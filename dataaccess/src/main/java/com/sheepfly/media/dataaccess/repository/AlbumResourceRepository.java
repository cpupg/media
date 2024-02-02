package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.AlbumResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumResourceRepository extends JpaRepository<AlbumResource, String>,
        JpaSpecificationExecutor<AlbumResource> {
}
