package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
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
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AlbumServiceImpl extends BaseJpaServiceImpl<Album, String, AlbumRepository> implements AlbumService {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AlbumServiceImpl.class);
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
        if (ObjectUtils.isNotEmpty(resourceData.getDeletedAlbums())) {
            long l = arMapper.batchUpdateByResource(resourceData);
            LOGGER.info("删除{}个专辑，涉及数据{}条", resourceData.getDeletedAlbums().size(), l);
        }
        if (ObjectUtils.isNotEmpty(resourceData.getAddedAlbums())) {
            LOGGER.info("处理新增专辑");
            List<String> albumList = resourceData.getAddedAlbums();
            TableResponse<ResourceVo> response = resourceService.queryResourceVoList(
                    resourceData.getCondition());
            LOGGER.info("给{}个资源设置{}个新专辑", response.getTotal(), albumList.size());
            List<ResourceVo> resourceList = response.getData();
            for (String id : albumList) {
                LOGGER.info("当前专辑:{}", id);
                for (ResourceVo resourceVo : resourceList) {
                    try {
                        resourceService.setAlbum(resourceVo.getId(), id);
                    } catch (BusinessException e) {
                        if (e.getError() == ErrorCode.RES_RA_NOT_REPEATED_AR) {
                            LOGGER.warn("{},资源{}已设置专辑{}", e.getMessage(), resourceVo.getId(), id);
                        }
                    }
                }
            }
            LOGGER.info("新增专辑处理完成");
        }
        LOGGER.info("专辑处理完成");
    }
}
