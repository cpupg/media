package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Collect;
import com.sheepfly.media.dataaccess.repository.CollectRepository;

/**
 * 收藏表服务。
 *
 * @author chen
 */
public interface CollectService extends BaseJpaService<Collect, String, CollectRepository> {
}
