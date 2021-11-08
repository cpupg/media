package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 资源类型。
 *
 * <p>一个资源可以对应多个类型，这写类型可以具有继承关系，也可以没有。在进行分类推荐时，除了推荐选择
 * 的类型，还会推荐已选择类型的子类型。</p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("RESOURCE_TYPE")
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;
    /***
     * 资源id。
     */
    @TableField("RESOURCE_ID")
    private String resourceId;
    /**
     * 类型码。
     */
    @TableField("TYPE_CODE")
    private String typeCode;
    /**
     * 创建时间。
     */
    @TableField("CREATE_TIME")
    private String createTime;
    /**
     * 更新时间。
     */
    @TableField("UPDATE_TIME")
    private String uptateTime;


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

    public String getUptateTime() {
        return uptateTime;
    }

    public void setUptateTime(String uptateTime) {
        this.uptateTime = uptateTime;
    }

    @Override
    public String toString() {
        return "ResourceType{" +
                "resourceId=" + resourceId +
                ", typeCode=" + typeCode +
                ", createTime=" + createTime +
                ", updateText=" + uptateTime +
                "}";
    }
}
