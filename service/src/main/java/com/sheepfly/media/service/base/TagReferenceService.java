package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.TagReferenceRepository;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;

public interface TagReferenceService extends BaseJpaService<TagReference, String, TagReferenceRepository> {
    /**
     * 引用类型1，资源。
     */
    Integer REF_TYPE_RESOURCE = 1;

    TableResponse<TagReferenceVo> queryTagReferenceList(
            TableRequest<Object, TagReferenceParam, Object> form);


    TagReference addTag(String tagId, String resourceId);
}
