package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Rate;
import com.sheepfly.media.dataaccess.repository.RateRepository;

/**
 * 评分服务。
 *
 * @author chen
 */
public interface RateService extends BaseJpaService<Rate, String, RateRepository> {
}
