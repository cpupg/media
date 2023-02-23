package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.data.ResourceData;
import com.sheepfly.media.service.IResourceService;
import com.sheepfly.media.util.BeanUtil;
import com.sheepfly.media.vo.ResourceVo;
import com.sheepfly.media.vo.common.ProTableObject;
import com.sheepfly.media.vo.common.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Controller
@RequestMapping(value = "/resource", produces = "application/json;charset=utf-8")
public class ResourceController {
    @Autowired
    private IResourceService service;
    @Autowired
    private Validator validator;

    @PostMapping("/queryResourceList")
    @ResponseBody
    public ProTableObject<ResourceVo> queryResourceList() {
        List<ResourceVo> resourceVoList = service.queryResourceVoList(null);
        return new ProTableObject<>(resourceVoList.size(), resourceVoList);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseData<ResourceVo> add(@RequestBody ResourceData resourceData) {
        Resource resource = BeanUtil.dataToEntity(resourceData, Resource.class);
        Set<ConstraintViolation<Resource>> resultSet = validator.validate(resource);
        if (!resultSet.isEmpty()) {
            throw new ConstraintViolationException("验证失败", resultSet);
        }
        Resource savedResource = service.save(resource);
        return ResponseData.success(savedResource);
    }
}

