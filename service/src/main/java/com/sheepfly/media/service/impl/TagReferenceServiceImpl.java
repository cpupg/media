package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.mapper.TagReferenceMapper;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import com.sheepfly.media.service.base.TagReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TagReferenceServiceImpl extends BaseJpaServiceImpl<TagReference, String, TagReferenceRepository>
        implements TagReferenceService {
    @Autowired
    private TagReferenceMapper mapper;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private TagReferenceRepository repository;

    @Override
    public ProTableObject<TagReferenceVo> queryTagReferenceList(
            ProComponentsRequestVo<Object, TagReferenceParam, Object> form) {
        TagReferenceParam param = form.getParams();
        Page<Object> page = PageMethod.startPage(param.getCurrent(), param.getPageSize());
        List<TagReferenceVo> list = mapper.queryTagReferenceList(form);
        return ProTableObject.success(list, page.getTotal());
    }


    @Override
    public TagReference addTag(String tagId, String resourceId) {
        TagReference tagReference = new TagReference();
        tagReference.setId(snowflake.nextIdStr());
        tagReference.setResourceId(resourceId);
        tagReference.setTagId(tagId);
        tagReference.setReferenceType(TagReferenceService.REF_TYPE_RESOURCE);
        tagReference.setReferTime(new Date());
        return repository.saveAndFlush(tagReference);
    }
}
