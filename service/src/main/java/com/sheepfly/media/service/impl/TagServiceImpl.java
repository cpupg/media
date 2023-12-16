package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.repository.TagsRepository;
import com.sheepfly.media.service.base.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseJpaServiceImpl<Tag, String, TagsRepository> implements TagService {
}
