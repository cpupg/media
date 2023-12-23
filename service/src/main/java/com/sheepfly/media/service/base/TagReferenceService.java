package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;

import java.util.List;

public interface TagReferenceService extends BaseJpaService<TagReference, String, TagReferenceRepository> {
    /**
     * 引用类型1，资源。
     */
    Integer REF_TYPE_RESOURCE = 1;

    ProTableObject<TagReferenceVo> queryTagReferenceList(
            ProComponentsRequestVo<Object, TagReferenceParam, Object> form);
}
