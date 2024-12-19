package com.sheepfly.media.service.base;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IResourceService extends BaseJpaService<Resource, String, ResourceRepository> {
    /**
     * 查询资源。
     *
     * @param form 查询表单。
     *
     * @return 满足条件的资源。
     */
    TableResponse<ResourceVo> queryResourceVoList(TableRequest<ResourceFilter, ResourceParam, ResourceSort> form);

    /**
     * 查询资源对应的标签。
     *
     * @param resourceId 资源主键。
     *
     * @return 标签列表。
     */
    List<TagReferenceVo> queryTagReferenceByResourceId(String resourceId);

    /**
     * 删除资源和关联表。
     *
     * @param id 资源主键。
     *
     * @return 被删除的资源。
     */
    Resource deleteResource(String id) throws BusinessException;

    /**
     * 查询指定数量的标签。
     *
     * @param resourceId 资源标识。
     *
     * @return 标签列表。
     */
    List<TagReferenceVo> queryTagReferenceByResourceIdAndCount(String resourceId);

    /**
     * 给资源设置专辑。
     *
     * @param resourceId 资源标识。
     * @param albumId 专辑标识。
     *
     * @return 关联对象。
     */
    AlbumResource setAlbum(String resourceId, String albumId) throws BusinessException;

    /**
     * 查询资源列表，返回内容只包含目录和文件名，查询条件只有目录和文件名。
     *
     * @param form 搜索条件。
     *
     * @return 资源列表。
     */
    TableResponse<ResourceVo> queryListByAlbum(TableRequest<ResourceFilter, ResourceParam, ResourceSort> form);

    /**
     * 批量删除。
     *
     * @param condition 删除条件。
     *
     * @return 删除结果。
     */
    List<Map<String, Object>> batchDelete(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition);

    /**
     * 批量更新。
     *
     * @param resourceData 更新条件。
     *
     * @return 更新结果。
     */
    List<Map<String, Object>> batchUpdate(ResourceData resourceData);
}
