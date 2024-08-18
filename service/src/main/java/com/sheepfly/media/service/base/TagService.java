package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.repository.TagRepository;

public interface TagService extends BaseJpaService<Tag, String, TagRepository> {
    TableResponse<TagVo> queryTagList(TagData tagData);

    /**
     * 根据资源批量更新标签。
     *
     * @param resourceData 搜索条件和更新内容。
     */
    void batchUpdateByResource(ResourceData resourceData);
}
