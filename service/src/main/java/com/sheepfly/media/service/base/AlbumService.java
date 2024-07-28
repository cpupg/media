package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;
import com.sheepfly.media.common.vo.AlbumVo;

public interface AlbumService extends BaseJpaService<Album, String, AlbumRepository> {
    /**
     * 搜索专辑列表。
     *
     * @param tableRequest 搜索条件。
     *
     * @return 搜索结果。
     */
    TableResponse<AlbumVo> queryAlbumList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);


    /**
     * 通过资源批量删除专辑（逻辑删除）。
     *
     * <p>目录和文件名必须是小写，删除操作是物理删除。</p>
     *
     * <p>此方法需要删除资源的操作提交后再执行，否则无效，因为筛选条件已改变。</p>
     *
     * @param condition 删除条件。
     *
     * @return 删除数量。
     */
    long batchDeleteByResource(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition);

    /**
     * 从专辑中删除资源。
     *
     * @param resourceId 资源标识。
     *
     * @return 删除数量。
     */
    long deleteResourceFromAlbum(String resourceId);
}
