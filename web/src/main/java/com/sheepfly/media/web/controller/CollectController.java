package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.util.IdUtil;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.CollectVo;
import com.sheepfly.media.common.vo.ResourceCollectVo;
import com.sheepfly.media.common.vo.constraintgroup.DeleteConstraint;
import com.sheepfly.media.common.vo.constraintgroup.InsertConstraint;
import com.sheepfly.media.common.vo.constraintgroup.UpdateConstraint;
import com.sheepfly.media.dataaccess.entity.Collect;
import com.sheepfly.media.dataaccess.entity.Collect_;
import com.sheepfly.media.dataaccess.entity.ResourceCollect;
import com.sheepfly.media.dataaccess.entity.ResourceCollect_;
import com.sheepfly.media.service.base.CollectService;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.ResourceCollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 收藏相关请求。
 *
 * @author chen 收藏表相关服务。
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectController.class);
    @Resource
    private CollectService collectService;
    @Resource
    private IResourceService resourceService;
    @Resource
    private ResourceCollectService resourceCollectService;

    /**
     * 创建收藏夹。
     *
     * @param vo 收藏夹参数。
     *
     * @return 收藏夹。
     */
    @PostMapping("/create")
    public ResponseData<Collect> create(@RequestBody @Validated(InsertConstraint.class) CollectVo vo) {
        LOGGER.info("创建收藏夹{}", vo.getCollectName());
        long count = collectService.count((r, q, b) -> b.equal(r.get(Collect_.COLLECT_NAME), vo.getCollectName()));
        if (count > 0) {
            throw new BusinessException(ErrorCode.COLLECT_EXISTS);
        }
        Collect collect = new Collect();
        BeanUtils.copyProperties(vo, collect);
        collect.setCollectId(IdUtil.getIdStr());
        collect.setDeleteStatus(Constant.NOT_DELETED);
        collect.setCreateTime(new Date());
        Collect saved = collectService.save(collect);
        return ResponseData.success(saved);
    }

    /**
     * 更新收藏夹。
     *
     * @param vo 更新参数。
     *
     * @return 更新结果。
     */
    @PostMapping("/update")
    public ResponseData<Collect> update(@RequestBody @Validated(UpdateConstraint.class) CollectVo vo) {
        LOGGER.info("更新收藏夹{}", vo.getCollectId());
        Collect collect = new Collect();
        BeanUtils.copyProperties(vo, collect);
        collect.setUpdateTime(new Date());
        Collect updated = collectService.update(collect);
        return ResponseData.success(updated);
    }

    /**
     * 删除收藏夹。
     *
     * @param vo 删除参数。
     *
     * @return 删除结果。
     */
    @PostMapping("/delete")
    public ResponseData<CollectVo> delete(@RequestBody @Validated(UpdateConstraint.class) CollectVo vo) {
        LOGGER.info("删除收藏夹{}", vo.getCollectId());
        CollectVo deletedVo = collectService.delete(vo);
        return ResponseData.success(deletedVo);
    }

    /**
     * 资源添加到收藏夹。
     *
     * @param vo 资源和收藏夹。
     *
     * @return 添加结果。
     */
    @PostMapping("/addCollect")
    public ResponseData<ResourceCollect> addCollect(@RequestBody @Validated(InsertConstraint.class) ResourceCollectVo vo) {
        LOGGER.info("将资源{}添加到收藏夹{}", vo.getCollectId(), vo.getResourceId());
        com.sheepfly.media.dataaccess.entity.Resource res = resourceService.findById(vo.getResourceId());
        if (res == null) {
            throw new BusinessException(ErrorCode.RES_NOT_FOUND);
        }
        Collect collect = collectService.findById(vo.getCollectId());
        if (collect == null) {
            throw new BusinessException(ErrorCode.COLLECT_NOT_FOUND);
        }
        long count = resourceCollectService.count(
                (r, q, b) -> b.equal(r.get(ResourceCollect_.RESOURCE_ID), vo.getResourceId()));
        if (count > 0) {
            throw new  BusinessException(ErrorCode.COLLECT_CONTAIN_RESOURCE);
        }
        ResourceCollect rc = new ResourceCollect();
        BeanUtils.copyProperties(vo, rc);
        rc.setId(IdUtil.getIdStr());
        rc.setCreateTime(new Date());
        rc.setDeleteStatus( Constant.NOT_DELETED);
        ResourceCollect save = resourceCollectService.save(rc);
        LOGGER.info("添加完成");
        return ResponseData.success(save);
    }

    /**
     * 从收藏夹中删除资源。
     *
     * @param vo 要删除的资源。
     *
     * @return 被删除的收藏夹-资源关联对象。。
     */
    @PostMapping("/cancelCollect")
    public ResponseData<ResourceCollectVo> cancelCollect(@RequestBody @Validated(DeleteConstraint.class) ResourceCollectVo vo) {
        LOGGER.info("将资源{}从收藏夹{}移除", vo.getResourceId(), vo.getCollectId());
        resourceCollectService.deleteById(vo.getResourceCollectId());
        LOGGER.info("移除完成");
        return ResponseData.success(vo);
    }

    /**
     * 查询收藏夹。
     *
     * @param tableRequest 查询参数。
     *
     * @return 查询结果。
     */
    @PostMapping("/queryCollect")
    public TableResponse<CollectVo> queryCollect(@RequestBody TableRequest<BaseFilterVo, CollectVo, BaseSortVo> tableRequest) {
        return collectService.queryAll(tableRequest);
    }

    /**
     * 查询收藏夹和资源的关联关系。
     *
     * @param tableRequest 查询请求。
     *
     * @return 查询结果。
     */
    @PostMapping("/queryResourceCollect")
    public TableResponse<ResourceCollectVo> queryResourceCollect(@RequestBody TableRequest<BaseFilterVo, ResourceCollectVo, BaseSortVo> tableRequest) {
        LOGGER.info("查询收藏夹下的资源");
        return resourceCollectService.queryAll(tableRequest);
    }
}
