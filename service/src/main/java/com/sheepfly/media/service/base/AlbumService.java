package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;
import com.sheepfly.media.dataaccess.vo.AlbumVo;

public interface AlbumService extends BaseJpaService<Album, String, AlbumRepository> {
    TableResponse<AlbumVo> queryAlbumList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);
}
