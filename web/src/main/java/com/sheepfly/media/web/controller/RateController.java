package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.vo.RateVo;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.RateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public RateVo rate(@RequestParam("resourceId") String resourceId, @RequestParam("rate") int rate) {
        if (rate < 0 || rate > 10) {
            throw new BusinessException(ErrorCode.RATE_RANGE_NOT_ALLOW);
        }
        com.sheepfly.media.dataaccess.entity.Resource res = resourceService.findById(resourceId);
        if (res == null) {
            throw new BusinessException(ErrorCode.RES_NOT_FOUND);
        }
        return rateService.rateResource(resourceId, rate);
    }
}
