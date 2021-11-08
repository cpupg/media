package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 媒体内容分类。
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("MEDIA_TYPE")
public class MediaType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 类型id。
     */
    @TableId("ID")
    private String id;
    /**
     * 类型码，为描述的hashcode。
     */
    @TableField("TYPE_CODE")
    private String typeCode;
    /**
     * 类型描述。
     */
    @TableField("TYPE_DESCRIPTION")
    private String typeDescription;
    /**
     * 创建时间。
     */
    @TableField("CREATE_TIME")
    private String createTime;
    /**
     * 更新时间。
     */
    @TableField("UPDATE_TIME")
    private String updateTime;
    /**
     * 状态，0正常，1失效。
     */
    @TableField("STATUS")
    private Integer status;
    /**
     * 父分类id。
     */
    @TableField("PARENT_ID")
    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "id=" + id +
                ", typeCode=" + typeCode +
                ", typeDescription=" + typeDescription +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", parentId=" + parentId +
                "}";
    }
}
