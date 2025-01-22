package com.sheepfly.media.service.impl;

import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.vo.CollectVo;
import com.sheepfly.media.dataaccess.entity.Collect;
import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import com.sheepfly.media.dataaccess.entity.ResourceCollect_;
import com.sheepfly.media.dataaccess.repository.CollectRepository;
import com.sheepfly.media.service.base.CollectService;
import com.sheepfly.media.service.base.ResourceCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl
        extends BaseJpaServiceImpl<Collect, String, CollectRepository>
        implements CollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectServiceImpl.class);
    @Resource
    private CollectRepository collectRepository;
    @Resource
    private ResourceCollectService resourceCollectService;

    @Override
    public CollectVo delete(CollectVo vo) {
        Specification<ResourceCollect> spec = (r, q, b) -> {
            Predicate p1 = b.equal(r.get(ResourceCollect_.COLLECT_ID), vo.getCollectId());
            Predicate p2 = b.equal(r.get(ResourceCollect_.DELETE_STATUS), Constant.NOT_DELETED);
            return b.and(p1, p2);
        };
        Date date = new Date();
        long count = resourceCollectService.count(spec);
        if (count > 0) {
            LOGGER.info("收藏夹{}下包含{}个资源", vo.getCollectId(), count);
            List<ResourceCollect> list = resourceCollectService.findList(spec);
            list.forEach(l -> {
                l.setDeleteStatus(Constant.DELETED);
                l.setDeleteTime(date);
            });
            List<ResourceCollect> updatedList = resourceCollectService.updateAll(list);
            LOGGER.info("收藏夹{}下的{}个资源删除弯沉", vo.getCollectId(), updatedList.size());
        }
        vo.setDeleteTime(date);
        Collect collect = new Collect();
        BeanUtils.copyProperties(vo, collect);
        Collect saved = collectRepository.save(collect);
        LOGGER.info("收藏夹{}删除完成", vo.getCollectId());
        BeanUtils.copyProperties(saved, vo);
        return vo;
    }
}
