package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.DirRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirRepoRepository extends JpaRepository<DirRepo, String>, JpaSpecificationExecutor<DirRepo> {
    /**
     * 查询目录代码最大的目录。
     *
     * @return 目录。
     */
    DirRepo queryFirstByOrderByDirCodeDesc();
}
