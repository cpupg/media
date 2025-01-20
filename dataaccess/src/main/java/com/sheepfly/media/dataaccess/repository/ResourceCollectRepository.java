package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ResourceCollect表的mapper。
 *
 * @author chen
 */
public interface ResourceCollectRepository extends JpaRepository<ResourceCollect, String>,
        JpaSpecificationExecutor<ResourceCollect> {
}




