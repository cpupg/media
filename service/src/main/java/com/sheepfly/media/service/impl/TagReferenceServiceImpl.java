package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.service.base.TagReferenceService;
import org.springframework.stereotype.Service;

@Service
public class TagReferenceServiceImpl extends BaseJpaServiceImpl<TagReference, String, TagReferenceRepository>
        implements TagReferenceService {
}
