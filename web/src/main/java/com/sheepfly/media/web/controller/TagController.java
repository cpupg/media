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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {
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
     * @param tagId 标签主键。
     * @param resourceId 资源主键。
     *
     * @return 标签引用。
     */
    @PostMapping("/addTagToResource")
    public ResponseData<TagReferenceVo> addTagToResource(@RequestParam("tagId") String tagId,
            @RequestParam("resourceId") String resourceId) {
        if (StringUtils.isBlank(tagId) || StringUtils.isBlank(resourceId)) {
            return ResponseData.fail(ErrorCode.TAG_RES_ID_AND_TAG_ID_CANT_BE_NULL);
        }
        TagReference tagReference = trfService.addTag(tagId, resourceId);
        TagVo tagVo = new TagVo();
        tagVo.setId(tagId);
        TagReferenceVo vo = new TagReferenceVo();
        tagReference.copyTo(vo);
        vo.setTagVo(tagVo);
        return ResponseData.success(vo);
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
            log.warn("标签{}在不存在，但是被资源{}引用", tagReference.getTagId(), tagReference.getResourceId());
            return ResponseData.fail(ErrorCode.RES_TAG_NOT_FOUND);
        }
        TagVo tagVo = new TagVo();
        tag.copyTo(tagVo);
        log.info("删除资源{}的标签{}删除成功", resourceId, referenceId);
        return ResponseData.success(tagVo);
    }
}
