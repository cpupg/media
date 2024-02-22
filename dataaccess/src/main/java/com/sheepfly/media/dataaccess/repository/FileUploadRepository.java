package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileUploadRepository extends JpaRepository<FileUpload, String>, JpaSpecificationExecutor<FileUpload> {

}
