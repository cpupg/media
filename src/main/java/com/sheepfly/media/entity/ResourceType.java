package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 资源类型。
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("RESOURCE_TYPE")
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("RESOURCE_ID")
    private String resourceId;

    @TableField("TYPE_CODE")
    private String typeCode;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("UPDATE_TEXT")
    private String updateText;


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateText() {
        return updateText;
    }

    public void setUpdateText(String updateText) {
        this.updateText = updateText;
    }

    @Override
    public String toString() {
        return "ResourceType{" +
                "resourceId=" + resourceId +
                ", typeCode=" + typeCode +
                ", createTime=" + createTime +
                ", updateText=" + updateText +
                "}";
    }
}
