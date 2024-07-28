package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.repository.TagRepository;
import com.sheepfly.media.common.vo.TagVo;

public interface TagService extends BaseJpaService<Tag, String, TagRepository> {
    TableResponse<TagVo> queryTagList(TagData tagData);

}
