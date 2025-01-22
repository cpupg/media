package com.sheepfly.media.service.base;

import com.sheepfly.media.common.vo.CollectVo;
import com.sheepfly.media.dataaccess.entity.Collect;
import com.sheepfly.media.dataaccess.repository.CollectRepository;

/**
 * 收藏表服务。
 *
 * @author chen
 */
public interface CollectService extends BaseJpaService<Collect, String, CollectRepository> {
    /**
     * 删除收藏夹。
     *
     * @param vo 要删除的收藏夹。
     *
     * @return 删除结果。
     */
    CollectVo delete(CollectVo vo);
}
