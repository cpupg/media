package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.AlbumResourceVo;

import java.util.List;

/**
 * @author wrote-code
 * @description 针对表【ALBUM_RESOURCE(资源专辑)】的数据库操作Mapper
 * @createDate 2024-02-02 22:34:35
 * @Entity com.sheepfly.media.dataaccess.entity.AlbumResource
 */
public interface AlbumResourceMapper {
    /**
     * 查询包含资源的专辑。
     *
     * @param tableRequest 查询条件。
     *
     * @return 查询结果。
     */
    List<AlbumResourceVo> selectAlbumResourceList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);

    /**
     * 通过资源批量删除专辑。
     *
     * <p>目录和文件名必须是小写，删除操作是物理删除。</p>
     *
     * @param condition 删除条件。
     *
     * @return 删除数量。
     */
    long batchDeleteAlbum(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition);

    /**
     * 从专辑中删除资源。
     *
     * @param resourceId 资源标识。
     *
     * @return 删除数量。
     */
    long updateResourceFromAlbum(String resourceId);
}




