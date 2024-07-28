package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.vo.TagVo;

import java.util.List;

public interface TagMapper {
    List<TagVo> queryTagListBy(TagData tagData);
}
