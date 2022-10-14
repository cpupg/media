package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 资源仓库。
 *
 * @author 东方红
 */
public interface ResourceRepository extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource> {
}