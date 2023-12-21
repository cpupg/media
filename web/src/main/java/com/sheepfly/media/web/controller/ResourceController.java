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
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.vo.TagVo;
import com.sheepfly.media.service.base.DirectoryService;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@RestController
@RequestMapping(value = "/resource", produces = "application/json;charset=utf-8")
@Slf4j
public class ResourceController {
    @Autowired
    private IResourceService service;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private ResourceRepository repository;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagReferenceService tagReferenceService;

    @PostMapping("/queryResourceList")
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
    public ResponseData<Resource> add(@RequestBody @Validated ResourceData resourceData)
            throws InvocationTargetException, IllegalAccessException, BusinessException {
        if (!resourceData.getDir().matches("^\"?[a-zA-Z]:(.*(?=[/\\\\])?)+\"?$")) {
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
    public ResponseData<ResourceVo> delete(@RequestBody @NotNull String id) throws BusinessException {
        Resource resource = service.safeLogicDeleteById(id, Resource.class, ErrorCode.DELETE_NOT_EXIST_DATA);
        return ResponseData.success(resource);
    }

    @PostMapping("addTag")
    public ResponseData<TagReferenceVo> addTag(@RequestParam("resourceId") String resourceId,
            @RequestParam("tagName") String tagName) {
        log.info("给资源{{}}添加标签{{}}", resourceId, tagName);
        if (StringUtils.isBlank(tagName)) {
            return ResponseData.fail(ErrorCode.RES_TAG_NAME_CANT_NULL);
        }
        if (tagName.length() > 10) {
            return ResponseData.fail(ErrorCode.TAG_NAME_TOO_LONG);
        }
        TagReference tagReference = service.createResourceTag(resourceId, tagName);
        TagReferenceVo vo = new TagReferenceVo();
        tagReference.copyTo(vo);
        Tag tag = tagService.findById(tagReference.getTagId());
        TagVo tagVo = new TagVo();
        tag.copyTo(tagVo);
        vo.setTagVo(tagVo);
        log.info("添加完成");
        return ResponseData.success(vo);
    }

    @PostMapping("deleteTag")
    public ResponseData<TagVo> deleteTag(@RequestParam("referenceId") String referenceId,
            @RequestParam("resourceId") String resourceId) {
        TagReference tagReference = tagReferenceService.findById(referenceId);
        if (tagReference == null) {
            return ResponseData.fail(ErrorCode.RES_TAG_NOT_FOUND);
        }
        if (!resourceId.equals(tagReference.getResourceId())) {
            return ResponseData.fail(ErrorCode.RES_DONT_HAVE_THIS_TAG);
        }
        service.deleteResourceTag(referenceId);
        Tag tag = tagService.findById(tagReference.getTagId());
        if (tag == null) {
            log.warn("标签{}在不存在，但是被资源{}引用", tagReference.getTagId(), tagReference.getResourceId());
            ResponseData.fail(ErrorCode.RES_TAG_NOT_FOUND);
        }
        TagVo tagVo = new TagVo();
        tag.copyTo(tagVo);
        log.info("删除资源{}的标签{}删除成功", resourceId, referenceId);
        return ResponseData.success(tagVo);
    }

    @PostMapping("/queryTags")
    public ResponseData<List<TagReferenceVo>> queryTags(HttpServletRequest request) {
        String resourceId = request.getParameter("resourceId");
        if (StringUtils.isBlank(resourceId)) {
            return ResponseData.fail(ErrorCode.REQUEST_VALUE_IS_LOST);
        }
        List<TagReferenceVo> list = service.queryTagReferenceByResourceId(resourceId);
        return ResponseData.success(list);
    }
}

