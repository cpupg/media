package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DirectoryRepository extends JpaRepository<Directory, String>, JpaSpecificationExecutor<Directory> {
    List<Directory> queryByLevelAndDeleteStatus(Integer level, Integer deleteStatus);

    List<Directory> queryByParentCodeAndDeleteStatus(Long parentCode, Integer deleteStatus);
}
