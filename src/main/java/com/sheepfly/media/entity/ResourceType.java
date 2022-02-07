package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 资源和类型关联
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@TableName("RESOURCE_TYPE")
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 资源id
     */
    @TableField("RESOURCE_ID")
    private String resourceId;

    /**
     * 类型id
     */
    @TableField("TYPE_ID")
    private String typeId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDate updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ResourceType{" +
        "id=" + id +
        ", resourceId=" + resourceId +
        ", typeId=" + typeId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
