package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.data.ResourceData;
import com.sheepfly.media.service.IResourceService;
import com.sheepfly.media.vo.ResourceVo;
import com.sheepfly.media.vo.common.ErrorCode;
import com.sheepfly.media.vo.common.ProTableObject;
import com.sheepfly.media.vo.common.ResponseData;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Validator;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

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
    public ResponseData<ResourceVo> add(@RequestBody @Validated ResourceData resourceData)
            throws InvocationTargetException, IllegalAccessException {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resource, resourceData);
        Date date = new Date();
        resource.setCreateTime(date);
        resource.setSaveTime(date);
        File file = new File(resource.getDir());
        if (file.isFile()) {
            resource.setFilename(file.getName());
            resource.setDir(file.getParentFile().getAbsolutePath());
        } else {
            resource.setDir(file.getAbsolutePath());
        }
        Resource savedResource = service.save(resource);
        return ResponseData.success(savedResource);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<ResourceVo> delete(@RequestBody String id) {
        Resource resource = service.findById(id);
        if (resource == null) {
            return ResponseData.fail(ErrorCode.DELETE_NOT_EXIST_DATA);
        } else {
            service.deleteById(id);
            return ResponseData.success();
        }
    }
}

