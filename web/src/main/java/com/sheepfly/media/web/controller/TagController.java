package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.vo.TagVo;
import com.sheepfly.media.service.base.TagReferenceService;
import com.sheepfly.media.service.base.TagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {
    @Autowired
    private TagService service;
    @Autowired
    private TagReferenceService trfService;

    @PostMapping("/queryTagList")
    public TableResponse<TagVo> queryTagList(@RequestBody TagData tagData) {
        if (StringUtils.isBlank(tagData.getName()) && !(tagData.isRate() || tagData.isFavourite())) {
            return TableResponse.fail(ErrorCode.TAG_NAME_CANT_BE_EMPTY.getMessage());
        }
        log.info("查询相似标签{}", tagData.getName());
        return service.queryTagList(tagData);
    }

    @PostMapping("/queryTagReferenceList")
    public TableResponse<TagReferenceVo> queryTagReferenceList(
            @RequestBody TableRequest<Object, TagReferenceParam, Object> form) {
        if (StringUtils.isBlank(form.getParams().getResourceId())) {
            return TableResponse.fail(ErrorCode.TAG_RES_ID_CANT_BE_NULL.getMessage());
        }
        return trfService.queryTagReferenceList(form);
    }

    /**
     * 增加标签。
     *
     * <p>使用tagId和resourceId直接增加标签，若tagReferenceId不为空，说明要添加同名标签，
     * 需要先删掉旧标签再添加新标签。</p>
     *
     * @param tagId 标签主键。
     * @param resourceId 资源主键。
     * @param tagReferenceId 标签引用。
     *
     * @return 标签引用。
     */
    @PostMapping("/addTag")
    public ResponseData<TagReferenceVo> addTag(@RequestParam("tagId") String tagId,
            @RequestParam("resourceId") String resourceId,
            @RequestParam(value = "tagReferenceId", required = false) String tagReferenceId) {
        if (StringUtils.isBlank(tagId) || StringUtils.isBlank(resourceId)) {
            return ResponseData.fail(ErrorCode.TAG_RES_ID_AND_TAG_ID_CANT_BE_NULL);
        }
        if (StringUtils.isNotBlank(tagReferenceId)) {
            trfService.deleteById(tagReferenceId);
        }
        TagReference tagReference = trfService.addTag(tagId, resourceId);
        TagVo tagVo = new TagVo();
        tagVo.setId(tagId);
        TagReferenceVo vo = new TagReferenceVo();
        tagReference.copyTo(vo);
        vo.setTagVo(tagVo);
        return ResponseData.success(vo);
    }
}
