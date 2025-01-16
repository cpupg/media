package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.common.vo.TagVo;
import com.sheepfly.media.dataaccess.entity.Tag;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TagController.class);
    @Resource
    private TagService service;
    @Resource
    private TagReferenceService trfService;

    /**
     * 模糊查询已有标签。
     *
     * @param tagData 查询条件。
     *
     * @return 查询结果。
     */
    @PostMapping("/queryList")
    public TableResponse<TagVo> queryList(@RequestBody TagData tagData) {
        if (StringUtils.isBlank(tagData.getName()) && !(tagData.isRate() || tagData.isFavourite())) {
            return TableResponse.fail(ErrorCode.TAG_NAME_CANT_BE_EMPTY.getMessage());
        }
        return service.queryTagList(tagData);
    }

    /**
     * 查询资源拥有的标签。
     *
     * @param resourceId 资源标识。
     *
     * @return 资源对应的标签。
     */
    @PostMapping("/queryResourceList")
    public ResponseData<List<TagReferenceVo>> queryTags(@RequestParam("resourceId") String resourceId) {
        if (StringUtils.isBlank(resourceId)) {
            return ResponseData.fail(ErrorCode.TAG_RES_ID_CANT_BE_NULL);
        }
        List<TagReferenceVo> list = service.queryTagReferenceByResourceId(resourceId);
        return ResponseData.success(list);
    }

    /**
     * 给资源添加标签。
     *
     * <p>标签通过搜索得到，因此可以使用resourceId+tagId直接添加。</p>
     *
     * @param tagData 表单数据。
     *
     * @return 标签引用。
     */
    @PostMapping("/addTagToResource")
    public ResponseData<TagReferenceVo> addTagToResource(@Validated @RequestBody TagData tagData) {
        TagReference tagReference = service.addTag(tagData);
        TagReferenceVo vo = new TagReferenceVo();
        BeanUtils.copyProperties(tagReference, vo);
        return ResponseData.success(vo);
    }


    /**
     * 临时请求，将来会删除。
     *
     * @param tagData 数据。
     *
     * @return 数据。
     */
    @PostMapping("/batchSetTag")
    public ResponseData<List<TagReferenceVo>> batchSetTag(@RequestBody TagData tagData) {
        String[] tags = tagData.getNames().split(",");
        String[] ids = tagData.getResourceIds().split(",");
        List<Map<String, Object>> list = new ArrayList<>();
        for (String id : ids) {
            LOGGER.info("当前资源:{}----", id);
            Map<String, Object> tagMap = new HashMap<>();
            for (String tag : tags) {
                LOGGER.info("当前标签:{}", tag);
                try {
                    TagReference tagReference = service.addTag(tagData);
                    tagMap.put(tag, tagReference);
                } catch (Exception e) {
                    LOGGER.error("资源{}添加标签{}失败", id, tag, e);
                    tagMap.put(tag, e.getMessage());
                }
            }
            Map<String, Object> idMap = new HashMap<>();
            idMap.put(id, tagMap);
            list.add(idMap);
        }
        return ResponseData.success(list);
    }

    /**
     * 删除标签。
     *
     * @param referenceId 要删除的标签引用主键。
     * @param resourceId 资源主键。
     *
     * @return 删除的标签引用。
     */
    @PostMapping("/removeTagFromResource")
    public ResponseData<TagVo> removeTagFromResource(@RequestParam("referenceId") String referenceId, @RequestParam("resourceId") String resourceId) {
        TagReference tagReference = trfService.findById(referenceId);
        if (tagReference == null) {
            return ResponseData.fail(ErrorCode.RES_TAG_NOT_FOUND);
        }
        if (!resourceId.equals(tagReference.getResourceId())) {
            return ResponseData.fail(ErrorCode.RES_DONT_HAVE_THIS_TAG);
        }
        trfService.deleteById(referenceId);
        Tag tag = service.findById(tagReference.getTagId());
        if (tag == null) {
            LOGGER.warn("标签{}在不存在，但是被资源{}引用", tagReference.getTagId(), tagReference.getResourceId());
            return ResponseData.fail(ErrorCode.RES_TAG_NOT_FOUND);
        }
        TagVo tagVo = new TagVo();
        tag.copyTo(tagVo);
        LOGGER.info("删除资源{}的标签{}删除成功", resourceId, referenceId);
        return ResponseData.success(tagVo);
    }
}
