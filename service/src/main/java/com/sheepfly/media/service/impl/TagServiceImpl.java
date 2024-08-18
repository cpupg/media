package com.sheepfly.media.service.impl;

import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.mapper.TagMapper;
import com.sheepfly.media.dataaccess.mapper.TagReferenceMapper;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TagServiceImpl extends BaseJpaServiceImpl<Tag, String, TagRepository> implements TagService {
    @Resource
    private TagMapper mapper;
    @Resource
    private TagReferenceMapper tagReferenceMapper;
    @Resource
    private IResourceService resourceService;

    @Override
    public TableResponse<TagVo> queryTagList(TagData tagData) {
        List<TagVo> list = mapper.queryTagListBy(tagData);
        return TableResponse.success(list, (long) list.size());
    }

    @Override
    public void batchUpdateByResource(ResourceData resourceData) {
        log.info("处理删除的标签");
        long l1 = tagReferenceMapper.batchUpdateByResource(resourceData);
        log.info("处理完成，共删除{}条数据，开始处理新增标签", l1);
        TableResponse<ResourceVo> response = resourceService.queryResourceVoList(resourceData.getCondition());
        List<ResourceVo> list = response.getData();
        log.info("一共{}个资源", list.size());
        List<TagVo> tagsList = resourceData.getAddedTags();
        for (TagVo tagVo : tagsList) {
            log.info("当前标签:{}", tagVo.getName());
            for (ResourceVo resourceVo : list) {
                resourceService.createResourceTag(resourceVo.getId(), tagVo.getId());
            }
        }
        log.info("新标签处理完成");
    }

}
