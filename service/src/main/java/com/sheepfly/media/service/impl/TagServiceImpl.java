package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.mapper.TagMapper;
import com.sheepfly.media.dataaccess.mapper.TagReferenceMapper;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TagServiceImpl extends BaseJpaServiceImpl<Tag, String, TagRepository> implements TagService {
    @Resource
    private TagMapper mapper;
    @Resource
    private TagReferenceMapper tagReferenceMapper;
    @Resource
    private IResourceService resourceService;
    @Resource
    private TagReferenceService trfService;
    @Resource
    private Snowflake snowflake;

    @Override
    public TableResponse<TagVo> queryTagList(TagData tagData) {
        List<TagVo> list = mapper.queryTagList(tagData);
        return TableResponse.success(list, (long) list.size());
    }

    @Override
    public void batchUpdateByResource(ResourceData resourceData) {
        if (ObjectUtils.isNotEmpty(resourceData.getDeletedTags())) {
            log.info("处理删除的标签");
            long l1 = tagReferenceMapper.batchUpdateByResource(resourceData);
            log.info("处理完成，涉及标签{}个，涉及数据{}条", resourceData.getDeletedTags().size(), l1);
        }
        if (ObjectUtils.isNotEmpty(resourceData.getAddedTags())) {
            TableResponse<ResourceVo> response = resourceService.queryResourceVoList(resourceData.getCondition());
            List<ResourceVo> list = response.getData();
            log.info("一共{}个资源", list.size());
            List<TagVo> tagsList = resourceData.getAddedTags();
            for (TagVo tagVo : tagsList) {
                log.info("当前标签:{}", tagVo.getName());
                TagData tagData = new TagData();
                for (ResourceVo resourceVo : list) {
                    tagData.setResourceId(resourceVo.getId());
                    tagData.setName(tagVo.getName());
                    addTag(tagData);
                }
            }
            log.info("新标签处理完成");
        }
    }

    @Override
    public List<TagReferenceVo> queryTagReferenceByResourceId(String resourceId) {
        return mapper.queryTagReferenceByResourceId(resourceId);
    }

    @Override
    public TagReference addTag(TagData tagData) {
        String resourceId = tagData.getResourceId();
        if (StringUtils.isNotEmpty(tagData.getTagId())) {
            return trfService.addTag(tagData.getTagId(), resourceId);
        }
        String name = tagData.getName();
        Tag tag = new Tag();
        tag.setName(name);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Optional<Tag> tagOpt = findOne(Example.of(tag, exampleMatcher));
        if (!tagOpt.isPresent()) {
            log.info("标签{{}}不存在，创建新标签", name);
            tag.setCreateTime(new Date());
            tag.setId(snowflake.nextIdStr());
            tag = save(tag);
            flush();
            log.info("新标签{} -> {}创建完成", tag.getId(), tag.getName());
        } else {
            tag = tagOpt.orElse(null);
        }
        TagReference tagReference = new TagReference();
        tagReference.setResourceId(resourceId);
        tagReference.setTagId(tag.getId());
        tagReference.setReferenceType(TagReferenceService.REF_TYPE_RESOURCE);
        Optional<TagReference> tagRefOpt = trfService.findOne(Example.of(tagReference));
        if (!tagRefOpt.isPresent()) {
            log.warn("给资源{{}}设置标签{} -> {}", resourceId, tag.getId(), tag.getName());
            tagReference.setId(snowflake.nextIdStr());
            tagReference.setReferTime(new Date());
            tagReference = trfService.save(tagReference);
            trfService.flush();
            log.info("给资源{{}}设置标签{{}}完成", resourceId, tag.getName());
        } else {
            tagReference = tagRefOpt.orElse(null);
        }
        return tagReference;
    }
}
