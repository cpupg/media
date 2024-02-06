package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;

import java.util.List;

public interface TagReferenceMapper {
    List<TagReferenceVo> queryTagReferenceList(
            TableRequest<Object, TagReferenceParam, Object> form);

    TagReferenceVo queryFavorite(String resourceId);

    TagReferenceVo queryRate(String resourceId);
}
