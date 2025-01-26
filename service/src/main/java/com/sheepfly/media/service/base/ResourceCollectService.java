package com.sheepfly.media.service.base;

import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.ResourceCollectVo;
import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import com.sheepfly.media.dataaccess.repository.ResourceCollectRepository;

/**
 * 资源-收藏表服务。
 *
 * @author chen
 */
public interface ResourceCollectService extends BaseJpaService<ResourceCollect, String, ResourceCollectRepository> {
    /**
     * 查询资源关联的收藏。
     *
     * @param tableRequest@return 查询结果。
     */
    TableResponse<ResourceCollectVo> queryAll(TableRequest<BaseFilterVo, ResourceCollectVo, BaseSortVo> tableRequest);
}
