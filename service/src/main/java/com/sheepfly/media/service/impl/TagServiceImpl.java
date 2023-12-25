package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.mapper.TagMapper;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.dataaccess.vo.TagVo;
import com.sheepfly.media.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends BaseJpaServiceImpl<Tag, String, TagRepository> implements TagService {
    @Autowired
    private TagMapper mapper;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private TagReferenceRepository trfRepository;

    @Override
    public ProTableObject<TagVo> queryTagList(TagData tagData) {
        List<TagVo> list = mapper.queryTagListBy(tagData);
        return ProTableObject.success(list, (long) list.size());
    }

}
