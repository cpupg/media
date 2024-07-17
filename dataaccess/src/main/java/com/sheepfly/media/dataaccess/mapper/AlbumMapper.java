package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.dataaccess.vo.AlbumVo;

import java.util.List;

/**
 * @author wrote-code
 * @description 针对表【ALBUM(专辑)】的数据库操作Mapper
 * @createDate 2024-02-02 22:21:42
 * @Entity com.sheepfly.media.dataaccess.entity.Album
 */
public interface AlbumMapper {
    /**
     * 查询专辑列表。
     *
     * @param tableRequest
     *
     * @return 查询结果。
     */
    List<AlbumVo> selectAlbumList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);
}
