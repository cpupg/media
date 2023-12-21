package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.entity.TagReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagReferenceRepository
        extends JpaRepository<TagReference, String>, JpaSpecificationExecutor<TagReference> {
    long deleteByResourceId(String resourceId);

}
