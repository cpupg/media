package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.TagData;
import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.common.http.ProTableObject;
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
    public ProTableObject<TagVo> queryTagList(@RequestBody TagData tagData) {
        if (StringUtils.isBlank(tagData.getName())) {
            return ProTableObject.fail(ErrorCode.TAG_NAME_CANT_BE_EMPTY.getMessage());
        }
        log.info("查询相似标签{}", tagData.getName());
        return service.queryTagList(tagData);
    }

    @PostMapping("/queryTagReferenceList")
    public ProTableObject<TagReferenceVo> queryTagReferenceList(
            @RequestBody ProComponentsRequestVo<Object, TagReferenceParam, Object> form) {
        if (StringUtils.isBlank(form.getParams().getResourceId())) {
            return ProTableObject.fail(ErrorCode.TAG_RES_ID_CANT_BE_NULL.getMessage());
        }
        return trfService.queryTagReferenceList(form);
    }
}
