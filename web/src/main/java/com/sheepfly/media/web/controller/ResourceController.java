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
import com.sheepfly.media.dataaccess.entity.Resource_;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.service.base.DirectoryService;
import com.sheepfly.media.service.base.IResourceService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Optional;

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
    @Autowired
    private ResourceRepository repository;

    @PostMapping("/queryResourceList")
    @ResponseBody
    public ProTableObject<ResourceVo> queryResourceList(
            @RequestBody ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form) {
        ResourceFilter params = form.getParams();
        if (StringUtils.isNotBlank(params.getDir())) {
            params.setDir(params.getDir().toLowerCase());
        }
        if (StringUtils.isNotBlank(params.getFilename())) {
            params.setFilename(params.getFilename().toLowerCase());
        }
        return service.queryResourceVoList(form);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseData add(@RequestBody @Validated ResourceData resourceData)
            throws InvocationTargetException, IllegalAccessException, BusinessException {
        String prefix = FilenameUtils.getPrefix(resourceData.getDir());
        if (!prefix.matches("^[a-zA-Z]:(/|\\\\)$")) {
            return ResponseData.fail(ErrorCode.DIRECTORY_ILLEGAL_DRIVER);
        }
        Resource resource = new Resource();
        BeanUtils.copyProperties(resource, resourceData);
        Date date = new Date();
        resource.setCreateTime(date);
        resource.setSaveTime(date);
        // 判断输入的路径是文件还是目录，方便直接复制全路径到表单
        File file = new File(resourceData.getDir());
        String parentDir = file.getAbsolutePath();
        log.info("文件目录{}", parentDir);
        if (file.isFile()) {
            log.info("当前资源是一个文件，计算父目录");
            resource.setFilename(file.getName());
            parentDir = FilenameUtils.normalize(file.getParent(), true);
        }
        parentDir = FilenameUtils.normalize(parentDir, true);
        if (!parentDir.endsWith(Constant.SEPERATOR)) {
            parentDir = parentDir + Constant.SEPERATOR;
        }
        // 盘符大写
        parentDir = parentDir.substring(0, 1).toUpperCase() + parentDir.substring(1);
        Directory directory = directoryService.queryDirectoryByPath(parentDir);
        if (directory == null) {
            directory = directoryService.createDirectory(parentDir);
        } else {
            // 检查重复文件
            Directory d = directory;
            Optional<Resource> opt = repository.findOne((r, q, b) -> {
                Predicate p1 = b.equal(r.get(Resource_.DIR_CODE), d.getDirCode());
                Predicate p2 = b.equal(r.get(Resource_.DELETE_STATUS), Constant.NOT_DELETED);
                Predicate p3 = b.equal(r.get(Resource_.FILENAME), resource.getFilename());
                return b.and(p1, p2, p3);
            });
            if (opt.isPresent()) {
                return ResponseData.fail(ErrorCode.RES_ADD_FAIL_BY_DUPLICATED);
            }
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

