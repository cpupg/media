package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.dataaccess.vo.TagVo;

public interface TagService extends BaseJpaService<Tag, String, TagRepository> {
    ProTableObject<TagVo> queryTagList(TagData tagData);
}
