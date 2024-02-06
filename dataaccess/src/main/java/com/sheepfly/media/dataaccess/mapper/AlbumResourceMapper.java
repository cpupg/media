package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.dataaccess.vo.AlbumResourceVo;

import java.util.List;

/**
 * @author wrote-code
 * @description 针对表【ALBUM_RESOURCE(资源专辑)】的数据库操作Mapper
 * @createDate 2024-02-02 22:34:35
 * @Entity com.sheepfly.media.dataaccess.entity.AlbumResource
 */
public interface AlbumResourceMapper {
    List<AlbumResourceVo> selectAlbumResourceList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);
}




