package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源仓库。
 *
 * @author wrote-code
 */
public interface ResourceRepository extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource> {
}
