package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.mapper.ResourceMapper;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagReferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Service
public class ResourceServiceImpl extends BaseJpaServiceImpl<Resource, String, ResourceRepository>
        implements IResourceService {
    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagReferenceRepository tagReferenceRepository;
    @Autowired
    private Snowflake snowflake;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public ProTableObject<ResourceVo> queryResourceVoList(
            ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form) {
        ResourceFilter params = form.getParams();
        Page<Object> page = PageHelper.startPage(params.getCurrent(), params.getPageSize());
        List<ResourceVo> list = resourceMapper.selectResourceVoList(form);
        // todo 1+n查询方案优化
        for (ResourceVo vo : list) {
            vo.setTagReferenceVoList(queryTagReferenceByResourceId(vo.getId()));
        }
        return ProTableObject.success(list, page.getTotal());
    }

    @Override
    public TagReference createResourceTag(String resourceId, String name) {
        Tag tag = new Tag();
        tag.setName(name);
        Optional<Tag> tagOpt = tagRepository.findOne(Example.of(tag));
        if (!tagOpt.isPresent()) {
            log.info("标签{{}}不存在，创建新标签", name);
            tag.setCreateTime(new Date());
            tag.setId(snowflake.nextIdStr());
            tag = tagRepository.saveAndFlush(tag);
            log.info("新标签{} -> {}创建完成", tag.getId(), tag.getName());
        } else {
            tag = tagOpt.orElse(null);
        }
        TagReference tagReference = new TagReference();
        tagReference.setResourceId(resourceId);
        tagReference.setTagId(tag.getId());
        tagReference.setReferenceType(TagReferenceService.REF_TYPE_RESOURCE);
        Optional<TagReference> tagRefOpt = tagReferenceRepository.findOne(Example.of(tagReference));
        if (!tagRefOpt.isPresent()) {
            log.warn("给资源{{}}设置标签{} -> {}", resourceId, tag.getId(), tag.getName());
            tagReference.setId(snowflake.nextIdStr());
            tagReference.setReferTime(new Date());
            tagReference = tagReferenceRepository.saveAndFlush(tagReference);
            log.info("给资源{{}}设置标签{{}}完成", resourceId, tag.getName());
        } else {
            tagReference = tagRefOpt.orElse(null);
        }
        return tagReference;
    }

    @Override
    public void deleteResourceTag(String tagReferenceId) {
        tagReferenceRepository.deleteById(tagReferenceId);
    }

    @Override
    public List<TagReferenceVo> queryTagReferenceByResourceId(String resourceId) {
        return resourceMapper.selectTagReferenceByResourceId(resourceId);
    }
}
