package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

public class TagReferenceVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 标签id
     */
    private TagVo tagVo;

    /**
     * 引用类型1:资源
     */
    private Integer referenceType;
    /**
     * 引用时间。
     */
    private Date referTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public TagVo getTagVo() {
        return this.tagVo;
    }

    public void setTagVo(TagVo tagVo) {
        this.tagVo = tagVo;
    }

    public Integer getReferenceType() {
        return this.referenceType;
    }

    public void setReferenceType(Integer referenceType) {
        this.referenceType = referenceType;
    }

    public Date getReferTime() {
        return this.referTime;
    }

    public void setReferTime(Date referTime) {
        this.referTime = referTime;
    }
}
