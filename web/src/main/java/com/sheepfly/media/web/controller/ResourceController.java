package com.sheepfly.media.web.controller;


import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.BatchTag;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.AlbumResourceVo;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.Resource_;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.service.base.AlbumResourceService;
import com.sheepfly.media.service.base.AlbumService;
import com.sheepfly.media.service.base.DirectoryService;
import com.sheepfly.media.service.base.IResourceService;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@SuppressWarnings({"java:S3740", "rawtypes"})
@RestController
@RequestMapping(value = "/resource", produces = "application/json;charset=utf-8")
@Slf4j
public class ResourceController {
    @Autowired
    private IResourceService service;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagReferenceService tagReferenceService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumResourceService arService;

    /**
     * 查询资源表格，用来在资源页展示。
     *
     * @param form 查询表单。
     *
     * @return 资源表格。
     */
    @PostMapping("/queryResourceList")
    public TableResponse<ResourceVo> queryResourceList(@RequestBody TableRequest<ResourceFilter, ResourceParam, ResourceSort> form) {
        ResourceParam params = form.getParams();
        if (StringUtils.isNotBlank(params.getDir())) {
            params.setDir(params.getDir().toLowerCase().replace("\\\\", "/"));
        }
        if (StringUtils.isNotBlank(params.getFilename())) {
            params.setFilename(params.getFilename().toLowerCase());
        }
        return service.queryResourceVoList(form);
    }

    /**
     * 查询资源表格，用来在弹框展示资源。
     *
     * @param form 查询条件。
     *
     * @return 表格。
     */
    @PostMapping("/queryList")
    public TableResponse<ResourceVo> queryList(@RequestBody TableRequest<ResourceFilter, ResourceParam, ResourceSort> form) {
        ResourceParam params = form.getParams();
        if (StringUtils.isNotBlank(params.getDir())) {
            params.setDir(params.getDir().toLowerCase().replace("\\\\", "/"));
        }
        if (StringUtils.isNotBlank(params.getFilename())) {
            params.setFilename(params.getFilename().toLowerCase());
        }
        return service.queryListByAlbum(form);
    }

    /**
     * 增加资源。
     *
     * @param resourceData 表单。
     *
     * @return 新增的资源。
     *
     * @throws InvocationTargetException e
     * @throws IllegalAccessException e
     * @throws BusinessException e
     */
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
            boolean repeat = service.checkRepeat((r, q, b) -> {
                Predicate p1 = b.equal(r.get(Resource_.DIR_CODE), d.getDirCode());
                Predicate p2 = b.equal(r.get(Resource_.DELETE_STATUS), Constant.NOT_DELETED);
                Predicate p3 = b.equal(r.get(Resource_.FILENAME), resource.getFilename());
                return b.and(p1, p2, p3);
            });
            if (repeat) {
                return ResponseData.fail(ErrorCode.RES_ADD_FAIL_BY_DUPLICATED);
            }
        }
        if (directory == null) {
            ResponseData.fail(ErrorCode.RESOURCE_MKDIR_FAIL);
        }
        resource.setDirCode(directory.getDirCode());
        if (resource.getCoverId() == null) {
            resource.setCoverId("");
        }
        Resource savedResource = service.save(resource);
        return ResponseData.success(savedResource);
    }

    /**
     * 删除资源。
     *
     * @param id 要删除的资源主键。
     *
     * @return 被删除的资源。
     *
     * @throws BusinessException e
     */
    @PostMapping("/delete")
    public ResponseData<Resource> delete(@RequestBody @NotNull String id) throws BusinessException {
        Resource res = service.deleteResource(id);
        return ResponseData.success(res);
    }

    /**
     * 查询起源下的标签。
     *
     * @param request 请求。
     *
     * @return 资源对应的标签。
     */
    @PostMapping("/queryTags")
    public ResponseData<List<TagReferenceVo>> queryTags(HttpServletRequest request) {
        String resourceId = request.getParameter("resourceId");
        if (StringUtils.isBlank(resourceId)) {
            return ResponseData.fail(ErrorCode.REQUEST_VALUE_IS_LOST);
        }
        List<TagReferenceVo> list = service.queryTagReferenceByResourceId(resourceId);
        return ResponseData.success(list);
    }

    /**
     * 查询专辑清单，可以使用
     *
     * @param tableRequest
     *
     * @return
     */
    @PostMapping("/queryAlbumList")
    public TableResponse<AlbumResourceVo> queryAlbumList(@RequestBody TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) {
        return albumService.queryAlbumResourceList(tableRequest);
    }

    @PostMapping("/setAlbum")
    public ResponseData<AlbumResource> setAlbum(@RequestParam String resourceId, @RequestParam String albumId)
            throws BusinessException {
        log.info("为资源{}设置专辑{}", resourceId, albumId);
        AlbumResource albumResource = service.setAlbum(resourceId, albumId);
        return ResponseData.success(albumResource);
    }

    /**
     * 从专辑里删除资源。
     *
     * <p>删除操作是逻辑删除。</p>
     *
     * @param albumResourceId 关联标识。
     *
     * @return 被删除的关联对象。
     */
    @PostMapping("/unsetAlbum")
    public ResponseData<AlbumResource> unsetAlbum(@RequestParam String albumResourceId) {
        log.info("移除专辑和资源关联关系{}", albumResourceId);
        AlbumResource albumResource = arService.logicDeleteById(albumResourceId, AlbumResource.class);
        return ResponseData.success(albumResource);
    }

    /**
     * 批量删除资源。
     *
     * <p>可以按勾选删除，也可以按搜索条件删除。搜索条件不包含标签。</p>
     *
     * @param data 删除条件。
     *
     * @return 删除结果。
     */
    @PostMapping("/batchDelete")
    public ResponseData<Object> batchDelete(@RequestBody TableRequest<ResourceFilter, ResourceParam, ResourceSort> data) {
        List<Map<String, Object>> list = service.batchDelete(data);
        return ResponseData.success(list);
    }

    /**
     * 批量更新。
     *
     * <p>可以按勾选更新，也可以按搜索条件更新。搜索条件不包含标签。</p>
     *
     * @return 更新结果。
     */
    @PostMapping("/batchUpdate")
    public ResponseData batchUpdate(@RequestBody ResourceData resourceData) {
        TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition = resourceData.getCondition();
        ResourceParam params = condition.getParams();
        if (StringUtils.isEmpty(params.getDir()) && StringUtils.isEmpty(params.getFilename()) &&
                StringUtils.isEmpty(params.getAuthorId()) && ObjectUtils.isEmpty(condition.getIdList())) {
            throw new BusinessException(ErrorCode.BATCH_UPDATE_CONDITION_LOST);
        }
        if (ObjectUtils.isEmpty(resourceData.getAddedTags()) && ObjectUtils.isEmpty(resourceData.getDeletedTags()) &&
                ObjectUtils.isEmpty(resourceData.getAddedAlbums()) && ObjectUtils.isEmpty(
                resourceData.getDeletedAlbums()) &&
                StringUtils.isEmpty(resourceData.getDir()) && StringUtils.isEmpty(resourceData.getFilename()) &&
                StringUtils.isEmpty(resourceData.getAuthorId())) {
            throw new BusinessException(ErrorCode.BATCH_UPDATE_CONTENT_LOST);
        }
        if (StringUtils.isNotEmpty(params.getDir())) {
            params.setDir(params.getDir().toLowerCase());
        }
        if (StringUtils.isNotEmpty(params.getFilename())) {
            params.setFilename(params.getFilename().toLowerCase());
        }
        List<Map<String, Object>> list = service.batchUpdate(resourceData);
        return ResponseData.success(list);
    }
}

