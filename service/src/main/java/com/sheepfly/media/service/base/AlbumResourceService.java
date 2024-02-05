package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.repository.AlbumResourceRepository;
import com.sheepfly.media.dataaccess.vo.AlbumResourceVo;

public interface AlbumResourceService extends BaseJpaService<AlbumResource, String, AlbumResourceRepository> {
    TableResponse<AlbumResourceVo> queryAlbumResourceList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);
}
