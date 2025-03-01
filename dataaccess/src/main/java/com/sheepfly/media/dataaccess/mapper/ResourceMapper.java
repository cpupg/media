package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Mapper
public interface ResourceMapper {
    /**
     * 查询资源。
     *
     * <p>文件名和目录名必须小写。</p>
     *
     * @param form 查询条件
     *
     * @return 资源列表。
     */
    List<ResourceVo> selectResourceVoList(TableRequest<ResourceFilter, ResourceParam, ResourceSort> form);

    /**
     * 根据资源搜索资源关联的标签。
     *
     * @param resourceId 资源标识。
     *
     * @return 查询结果。
     */
    List<TagReferenceVo> selectTagReferenceByResourceId(String resourceId);

    /**
     * 查询资源的标签并限制返回数量。
     *
     * @param resourceId 资源标识。
     * @param limitCount 限制数量。
     *
     * @return 查询结果。
     */
    List<TagReferenceVo> queryTagReferenceByResourceIdAndCount(@Param("resourceId") String resourceId,
            @Param("limitCount") Integer limitCount);

    /**
     * 根据专辑id和文件名查询资源。
     *
     * <p>传入的文件名必须是小写。</p>
     *
     * @param form 查询条件。
     *
     * @return 查询结果。
     */
    List<ResourceVo> queryListByAlbum(TableRequest<ResourceFilter, ResourceParam, ResourceSort> form);

    /**
     * 批量删除。
     *
     * <p>必须保证param中有指定参数，否则会全量更新。</p>
     *
     * @param param 查询参数。
     *
     * @return 删除数量。
     */
    int batchDelete(TableRequest<ResourceFilter, ResourceParam, ResourceSort> param);

    /**
     * 批量更新，只更新目录和作者。
     *
     * @param resourceData 更新条件。
     *
     * @return 更新数量。
     */
    int batchUpdate(ResourceData resourceData);
}
