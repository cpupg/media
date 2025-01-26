package com.sheepfly.media.service.impl;

import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.util.IdUtil;
import com.sheepfly.media.common.vo.RateVo;
import com.sheepfly.media.dataaccess.entity.Rate;
import com.sheepfly.media.dataaccess.repository.RateRepository;
import com.sheepfly.media.service.base.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 评分实现。
 *
 * @author chen
 */
@Service
public class RateServiceImpl extends BaseJpaServiceImpl<Rate, String, RateRepository> implements RateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateServiceImpl.class);

    @Override
    public RateVo rateResource(String resourceId, int rateNum) {
        LOGGER.info("为资源{}添加评分{}", resourceId, rateNum);
        Rate rate = new Rate();
        rate.setResourceId(resourceId);
        Optional<Rate> oldRate = findOne(Example.of(rate));
        rate = oldRate.orElse(rate);
        if (rate.getRateId() == null) {
            rate.setRateId(IdUtil.getIdStr());
            rate.setCreateTime(new Date());
            rate.setDeleteStatus(Constant.NOT_DELETED);
        } else {
            LOGGER.info("资源{}评分从{}改为{}", resourceId, rate.getRateId(), rate.getCreateTime());
            rate.setUpdateTime(new Date());
        }
        rate.setRate(rateNum);
        Rate save = save(rate);
        RateVo rateVo = new RateVo();
        BeanUtils.copyProperties(save, rateVo);
        LOGGER.info("评分完成");
        return rateVo;
    }
}
