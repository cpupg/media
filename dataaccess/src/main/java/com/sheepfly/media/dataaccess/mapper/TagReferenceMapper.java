package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;

import java.util.List;

public interface TagReferenceMapper {
    List<TagReferenceVo> queryTagReferenceList(
            TableRequest<Object, TagReferenceParam, Object> form);

    /**
     * 查询收藏。
     *
     * @param resourceId 资源标识。
     *
     * @return 资源对应的收藏对象。
     */
    TagReferenceVo queryFavorite(String resourceId);

    /**
     * 查询资源评分。
     *
     * @param resourceId 资源标识。
     *
     * @return 评分。
     */
    TagReferenceVo queryRate(String resourceId);

    /**
     * 根据资源批量删除标签。
     *
     * <p>资源的目录名和文件名必须小写。</p>
     *
     * @param condition 删除条件。
     *
     * @return 删除结果。
     */
    long batchDeleteByResource(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition);
}
