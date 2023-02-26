package com.sheepfly.media.repository;

import com.sheepfly.media.entity.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源-类型仓库。
 *
 * @author wrote-code
 */
public interface ResourceTypeRepository
        extends JpaRepository<ResourceType, String>, JpaSpecificationExecutor<ResourceType> {
}
