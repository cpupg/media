package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.mapper.AlbumMapper;
import com.sheepfly.media.dataaccess.mapper.AlbumResourceMapper;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;
import com.sheepfly.media.dataaccess.vo.AlbumVo;
import com.sheepfly.media.service.base.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlbumServiceImpl extends BaseJpaServiceImpl<Album, String, AlbumRepository> implements AlbumService {
    @Resource
    private AlbumMapper mapper;
    @Resource
    private AlbumResourceMapper arMapper;

    @Override
    public TableResponse<AlbumVo> queryAlbumList(TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) {
        AlbumParam params = tableRequest.getParams();
        Page<Object> page = PageMethod.startPage(params.getCurrent(), params.getPageSize());
        List<AlbumVo> list = mapper.selectAlbumList(tableRequest);
        return TableResponse.success(list, page.getTotal());
    }

    @Override
    public long batchDeleteByResource(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition) {
        return arMapper.batchDeleteAlbum(condition);
    }
}
