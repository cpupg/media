package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 标签引用
 */
@Entity
@Table(name = "MEDIA.TAG_REFERENCE")
public class TagReference implements Serializable, LogicDelete, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    /**
     * 资源id
     */
    @Column(name = "RESOURCE_ID", nullable = false)
    private String resourceId;

    /**
     * 标签id
     */
    @Column(name = "TAG_ID", nullable = false)
    private String tagId;

    /**
     * 引用类型1:资源
     */
    @Column(name = "REFERENCE_TYPE", nullable = false)
    private Integer referenceType;

    /**
     * 引用时间
     */
    @Column(name = "REFER_TIME", nullable = false)
    private Date referTime;

    /**
     * 取消引用的时间
     */
    @Column(name = "UN_REFER_TIME")
    private Date unReferTime;

    @Override
    public Date getUpdateTime() {
        // 应付语法检查，没有实际用途
        return null;
    }

    @Override
    public void setUpdateTime(Date date) {
        // 应付语法检查，没有实际用途
    }

    @Override
    public Integer getDeleteStatus() {
        // 应付语法检查，没有实际用途
        return null;
    }

    @Override
    public void setDeleteStatus(Integer deleteStatus) {
        // 应付语法检查，没有实际用途
    }

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

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
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

    public Date getUnReferTime() {
        return this.unReferTime;
    }

    public void setUnReferTime(Date unReferTime) {
        this.unReferTime = unReferTime;
    }

}
