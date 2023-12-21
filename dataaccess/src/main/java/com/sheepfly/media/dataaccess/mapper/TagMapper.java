package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.vo.TagVo;

import java.util.List;

public interface TagMapper {
    List<TagVo> queryTagListByName(String name);
}
