package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.common.vo.TagVo;

import java.util.List;

public interface TagMapper {
    List<TagVo> queryTagList(TagData tagData);

    /**
     * 根据资源搜索资源关联的标签。
     *
     * @param resourceId 资源标识。
     *
     * @return 查询结果。
     */
    List<TagReferenceVo> queryTagReferenceByResourceId(String resourceId);
}
