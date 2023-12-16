package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;

public interface TagReferenceService extends BaseJpaService<TagReference, String, TagReferenceRepository> {
    /**
     * 引用类型1，资源。
     */
    Integer REF_TYPE_RESOURCE = 1;
}
