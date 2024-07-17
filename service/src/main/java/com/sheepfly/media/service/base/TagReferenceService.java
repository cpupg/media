package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
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

    /**
     * 获取资源评分，如果没有评分，返回-1。
     *
     * @param resourceId 资源标识。
     *
     * @return 评分。
     */
    int getRate(String resourceId);

    /**
     * 获取收藏状态。已收藏返回true，否则返回false。
     *
     * @param resourceId 资源标识。
     *
     * @return 收藏状态。
     */
    boolean getFavorite(String resourceId);

    /**
     * 通过资源标识删除标签。
     *
     * @param id 资源标识。
     *
     * @return 删除数量。
     */
    long deleteByResourceId(String id);

    /**
     * 根据资源删除标签。
     *
     * @param condition 搜索条件。
     *
     * @return 删除数量。
     */
    long batchDeleteByResource(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition);
}
