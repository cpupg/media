package com.sheepfly.media.service.impl;

import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.mapper.TagMapper;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.service.base.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends BaseJpaServiceImpl<Tag, String, TagRepository> implements TagService {
    @Autowired
    private TagMapper mapper;

    @Override
    public TableResponse<TagVo> queryTagList(TagData tagData) {
        List<TagVo> list = mapper.queryTagListBy(tagData);
        return TableResponse.success(list, (long) list.size());
    }

}
