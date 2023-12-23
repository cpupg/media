package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;

import java.util.List;

public interface TagReferenceMapper {
    List<TagReferenceVo> queryTagReferenceList(
            ProComponentsRequestVo<Object, TagReferenceParam, Object> form);
}
