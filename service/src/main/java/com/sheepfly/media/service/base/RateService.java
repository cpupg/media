package com.sheepfly.media.service.base;

import com.sheepfly.media.common.vo.RateVo;
import com.sheepfly.media.dataaccess.entity.Rate;
import com.sheepfly.media.dataaccess.repository.RateRepository;

/**
 * 评分服务。
 *
 * @author chen
 */
public interface RateService extends BaseJpaService<Rate, String, RateRepository> {
    /**
     * 对资源进行评分。
     *
     * @param resourceId 资源主键标识。
     * @param rateNum 评分。
     *
     * @return 评分对象。
     */
    RateVo rateResource(String resourceId, int rateNum);
}
