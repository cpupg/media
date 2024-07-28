package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.mapper.AlbumResourceMapper;
import com.sheepfly.media.dataaccess.repository.AlbumResourceRepository;
import com.sheepfly.media.common.vo.AlbumResourceVo;
import com.sheepfly.media.service.base.AlbumResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumResourceServiceImpl extends BaseJpaServiceImpl<AlbumResource, String, AlbumResourceRepository>
        implements AlbumResourceService {
    @Autowired
    private AlbumResourceMapper mapper;

    @Override
    public TableResponse<AlbumResourceVo> queryAlbumResourceList(
            TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) {
        AlbumParam params = tableRequest.getParams();
        Page<Object> page = PageMethod.startPage(params.getCurrent(), params.getPageSize());
        List<AlbumResourceVo> list = mapper.selectAlbumResourceList(tableRequest);
        return TableResponse.success(list, page.getTotal());
    }
}
