package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.mapper.TagReferenceMapper;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.service.base.TagReferenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TagReferenceServiceImpl extends BaseJpaServiceImpl<TagReference, String, TagReferenceRepository>
        implements TagReferenceService {

    // 新方法写在TagService中，TagReferenceService不再新增方法。

    @Resource
    private TagReferenceMapper tagReferenceMapper;
    @Resource
    private Snowflake snowflake;
    @Resource
    private TagReferenceRepository tagReferenceRepository;

    @Override
    public TableResponse<TagReferenceVo> queryTagReferenceList(TableRequest<Object, TagReferenceParam, Object> form) {
        TagReferenceParam param = form.getParams();
        Page<Object> page = PageMethod.startPage(param.getCurrent(), param.getPageSize());
        List<TagReferenceVo> list = tagReferenceMapper.queryTagReferenceList(form);
        return TableResponse.success(list, page.getTotal());
    }


    @Override
    public TagReference addTag(String tagId, String resourceId) {
        TagReference tagReference = new TagReference();
        tagReference.setId(snowflake.nextIdStr());
        tagReference.setResourceId(resourceId);
        tagReference.setTagId(tagId);
        tagReference.setReferenceType(TagReferenceService.REF_TYPE_RESOURCE);
        tagReference.setReferTime(new Date());
        return tagReferenceRepository.saveAndFlush(tagReference);
    }

    @Override
    public int getRate(String resourceId) {
        TagReferenceVo trf = tagReferenceMapper.queryRate(resourceId);
        // note trf表是逻辑删除，trf.getTagVo() == null?
        return trf == null ? -1 : Integer.parseInt(trf.getTagVo().getName());
    }

    @Override
    public boolean getFavorite(String resourceId) {
        TagReferenceVo trf = tagReferenceMapper.queryFavorite(resourceId);
        return trf != null;
    }

    @Override
    public long deleteByResourceId(String id) {
        return tagReferenceRepository.deleteByResourceId(id);
    }

    @Override
    public long batchDeleteByResource(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition) {
        return tagReferenceMapper.batchDeleteByResource(condition);
    }
}
