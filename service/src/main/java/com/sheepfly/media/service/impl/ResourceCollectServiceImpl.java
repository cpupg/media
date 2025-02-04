package com.sheepfly.media.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.ResourceCollectVo;
import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import com.sheepfly.media.dataaccess.mapper.ResourceCollectMapper;
import com.sheepfly.media.dataaccess.repository.ResourceCollectRepository;
import com.sheepfly.media.service.base.ResourceCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceCollectServiceImpl
        extends BaseJpaServiceImpl<ResourceCollect, String, ResourceCollectRepository>
        implements ResourceCollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceCollectServiceImpl.class);

    @Resource
    private ResourceCollectMapper resourceCollectMapper;

    @Override
    public TableResponse<ResourceCollectVo> queryAll(TableRequest<BaseFilterVo, ResourceCollectVo, BaseSortVo> tableRequest) {
        ResourceCollectVo params = tableRequest.getParams();
        Page<ResourceCollectVo> page = PageMethod.startPage(params.getCurrent(), params.getPageSize());
        List<ResourceCollectVo> list = resourceCollectMapper.queryAll(tableRequest);
        return TableResponse.success(list, page.getTotal());
    }
}
