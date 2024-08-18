package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.AlbumResourceVo;
import com.sheepfly.media.common.vo.AlbumVo;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.mapper.AlbumMapper;
import com.sheepfly.media.dataaccess.mapper.AlbumResourceMapper;
import com.sheepfly.media.dataaccess.repository.AlbumRepository;
import com.sheepfly.media.service.base.AlbumService;
import com.sheepfly.media.service.base.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class AlbumServiceImpl extends BaseJpaServiceImpl<Album, String, AlbumRepository> implements AlbumService {
    @Resource
    private AlbumMapper mapper;
    @Resource
    private AlbumResourceMapper arMapper;
    @Resource
    private IResourceService resourceService;

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

    @Override
    public long deleteResourceFromAlbum(String resourceId) {
        return arMapper.updateResourceFromAlbum(resourceId);
    }

    @Override
    public TableResponse<AlbumResourceVo> queryAlbumResourceList(
            TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) {
        AlbumParam params = tableRequest.getParams();
        Page<Object> page = PageMethod.startPage(params.getCurrent(), params.getPageSize());
        List<AlbumResourceVo> list = arMapper.selectAlbumResourceList(tableRequest);
        return TableResponse.success(list, page.getTotal());
    }

    @Override
    public void batchUpdateByResource(ResourceData resourceData) {
        log.info("处理需要删除的专辑");
        long l = arMapper.batchUpdateByResource(resourceData);
        log.info("删除{}个专辑，处理新增的专辑", l);
        List<AlbumVo> albumList = resourceData.getAddedAlbums();
        TableResponse<ResourceVo> response = resourceService.queryResourceVoList(
                resourceData.getCondition());
        log.info("给{}个资源设置{}个新专辑", response.getTotal(), albumList.size());
        List<ResourceVo> resourceList = response.getData();
        for (AlbumVo albumVo : albumList) {
            log.info("当前专辑:{}", albumVo.getName());
            for (ResourceVo resourceVo : resourceList) {
                resourceService.setAlbum(resourceVo.getId(), albumVo.getId());
            }
        }
        log.info("专辑处理完成");
    }
}
