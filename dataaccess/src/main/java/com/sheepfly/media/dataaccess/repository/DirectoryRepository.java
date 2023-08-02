package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirectoryRepository extends JpaRepository<Directory, String>, JpaSpecificationExecutor<Directory> {

}
