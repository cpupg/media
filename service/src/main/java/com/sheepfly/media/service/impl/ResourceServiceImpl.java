package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.entity.TagReference_;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import com.sheepfly.media.dataaccess.mapper.ResourceMapper;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import com.sheepfly.media.service.base.AlbumResourceService;
import com.sheepfly.media.service.base.AlbumService;
import com.sheepfly.media.service.base.FileService;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    private TagService tagService;
    @Autowired
    private TagReferenceService trfService;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumResourceService arService;
    @Autowired
    private FileService fileService;

    @Autowired
    private ResourceMapper mapper;

    @Override
    public TableResponse<ResourceVo> queryResourceVoList(TableRequest<ResourceParam, ResourceParam, Object> form) {
        ResourceParam params = form.getParams();
        Page<Object> page = PageHelper.startPage(params.getCurrent(), params.getPageSize());
        List<ResourceVo> list = mapper.selectResourceVoList(form);
        for (int i = 0; i < list.size(); i++) {
            ResourceVo vo = list.get(i);
            String id = vo.getId();

            // todo 1+n查询方案优化
            // todo 临时优化：生产环境标签多，查询时只返回3个以优化性能
            if (i >= 5) {
                vo.setTagReferenceVoList(Collections.emptyList());
            }
            vo.setTagReferenceVoList(queryTagReferenceByResourceIdAndCount(id));
            long count = trfService.count(
                    (r, q, b) -> b.equal(r.get(TagReference_.RESOURCE_ID), id));
            vo.setTagCount(count);
            vo.setFavorite(trfService.getFavorite(id));
            vo.setRate(trfService.getRate(id));
        }
        return TableResponse.success(list, page.getTotal());
    }

    @Override
    public TagReference createResourceTag(String resourceId, String name) {
        Tag tag = new Tag();
        tag.setName(name);
        Optional<Tag> tagOpt = tagService.findOne(Example.of(tag));
        if (!tagOpt.isPresent()) {
            log.info("标签{{}}不存在，创建新标签", name);
            tag.setCreateTime(new Date());
            tag.setId(snowflake.nextIdStr());
            tag = tagService.save(tag);
            tagService.flush();
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

    @Override
    public void deleteResourceTag(String tagReferenceId) {
        trfService.deleteById(tagReferenceId);
    }

    @Override
    public List<TagReferenceVo> queryTagReferenceByResourceId(String resourceId) {
        return mapper.selectTagReferenceByResourceId(resourceId);
    }

    @Override
    public List<TagReferenceVo> queryTagReferenceByResourceIdAndCount(String resourceId) {
        return mapper.queryTagReferenceByResourceIdAndCount(resourceId, 5);
    }

    @Override
    public AlbumResource setAlbum(String resourceId, String albumId) throws BusinessException {
        if (!logicExistById(resourceId)) {
            throw new BusinessException(ErrorCode.RES_RA_RES_NOT_EXISTS);
        }
        if (!albumService.logicExistById(albumId)) {
            throw new BusinessException(ErrorCode.RES_RA_ALBUM_EXISTS);
        }
        AlbumResource ar = new AlbumResource();
        ar.setResourceId(resourceId);
        ar.setAlbumId(albumId);
        ar.setDeleteStatus(Constant.NOT_DELETED);
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        if (arService.count(Example.of(ar, matcher)) > 0) {
            throw new BusinessException(ErrorCode.RES_RA_NOT_REPEATED_AR);
        }
        ar.setId(snowflake.nextIdStr());
        ar.setCreateTime(new Date());
        ar.setDeleteStatus(LogicDelete.NOT_DELETED);
        AlbumResource save = arService.save(ar);
        arService.flush();
        return save;
    }

    @Override
    public TableResponse<ResourceVo> queryList(TableRequest<ResourceFilter, ResourceParam, ResourceSort> form) {
        ResourceParam params = form.getParams();
        Page<Object> page = PageMethod.startPage(params.getCurrent(), params.getPageSize());
        List<ResourceVo> list = mapper.queryList(form);
        return TableResponse.success(list, page.getTotal());
    }

    @Override
    public Resource deleteResource(String id) throws BusinessException {
        if (Constant.DELETED != logicDeleteById(id, Resource.class).getDeleteStatus()) {
            throw new BusinessException(ErrorCode.DELETE_NOT_EXIST_DATA);
        }
        log.info("删除资源{}的标签", id);
        long l = trfService.deleteByResourceId(id);
        log.info("删除{}个标签", l);
        int i = fileService.deleteFileByBusinessCode(id);
        log.info("删除资源{}的预览图count={}", id, i);
        return findById(id);
    }
}
