package com.sheepfly.media.web.controller;


import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.service.base.DirectoryService;
import com.sheepfly.media.service.base.IResourceService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

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
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    private IResourceService service;
    @Autowired
    private DirectoryService directoryService;

    @PostMapping("/queryResourceList")
    @ResponseBody
    public ProTableObject<ResourceVo> queryResourceList(
            @RequestBody ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form) {
        return service.queryResourceVoList(form);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseData add(@RequestBody @Validated ResourceData resourceData)
            throws InvocationTargetException, IllegalAccessException, BusinessException {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resource, resourceData);
        Date date = new Date();
        resource.setCreateTime(date);
        resource.setSaveTime(date);
        // 判断输入的路径是文件还是目录，方便直接复制全路径到表单
        File file = new File(resource.getDir());
        String parentDir = file.getAbsolutePath();
        log.info("文件目录{}", parentDir);
        if (file.isFile()) {
            log.info("当前资源是一个文件，计算父目录");
            resource.setFilename(file.getName());
            parentDir = file.getParent();
        }
        if (!parentDir.endsWith(Constant.SEPERATOR)) {
            parentDir = parentDir + Constant.SEPERATOR;
        }
        Directory directory = directoryService.queryDirectoryByPath(parentDir);
        if (directory == null) {
            directory = directoryService.addDirectory(parentDir);
        }
        if (directory == null) {
            ResponseData.fail(ErrorCode.RESOURCE_MKDIR_FAIL);
        }
        resource.setDirCode(directory.getDirCode());
        Resource savedResource = service.save(resource);
        return ResponseData.success(savedResource);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseData<ResourceVo> delete(@RequestBody @NotNull String id) throws BusinessException {
        Resource resource = service.safeLogicDeleteById(id, Resource.class, ErrorCode.DELETE_NOT_EXIST_DATA);
        return ResponseData.success(resource);
    }
}

