package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.vo.RateVo;
import com.sheepfly.media.dataaccess.entity.Rate;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.RateService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 评分请求。
 *
 * @author chen
 */
@RestController
@RequestMapping("/rate")
public class RateController {
    @Resource
    private RateService rateService;
    @Resource
    private IResourceService resourceService;

    /**
     * 添加评分。
     *
     * @param resourceId 资源标识。
     * @param rate 评分。
     *
     * @return 评分对象。
     */
    @PostMapping("/rate")
    public ResponseData<RateVo> rate(@RequestParam("resourceId") String resourceId, @RequestParam("rate") int rate) {
        if (rate < 0 || rate > 10) {
            throw new BusinessException(ErrorCode.RATE_RANGE_NOT_ALLOW);
        }
        com.sheepfly.media.dataaccess.entity.Resource res = resourceService.findById(resourceId);
        if (res == null) {
            throw new BusinessException(ErrorCode.RES_NOT_FOUND);
        }
        RateVo rateVo = rateService.rateResource(resourceId, rate);
        return ResponseData.success(rateVo);
    }

    /**
     * 获取资源的评分。
     *
     * @param resourceId
     *
     * @return 评分。
     */
    @PostMapping("/getRate")
    public ResponseData<RateVo> getRate(@RequestParam("resourceId") String resourceId) {
        Optional<Rate> one = rateService.findOne((r, q, b) -> b.equal(r.get("resourceId"), resourceId));
        if (one.isPresent()) {
            RateVo rateVo = new RateVo();
            BeanUtils.copyProperties(one.get(), rateVo);
            return ResponseData.success(rateVo);
        }
        return ResponseData.success(null);
    }
}
