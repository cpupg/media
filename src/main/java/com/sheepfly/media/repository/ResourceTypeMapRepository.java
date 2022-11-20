package com.sheepfly.media.repository;

import com.sheepfly.media.entity.ResourceTypeMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源-类型关联关系。
 *
 * @author wrote-code
 */
public interface ResourceTypeMapRepository
        extends JpaRepository<ResourceTypeMap, String>, JpaSpecificationExecutor<ResourceTypeMap> {
}
