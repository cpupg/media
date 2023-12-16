package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagsRepository extends JpaRepository<Tags, String>, JpaSpecificationExecutor<Tags> {

}
