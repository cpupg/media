package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-04
 */
@TableName("RESOURCE_TYPE")
public class ResourceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("RESOURCE_ID")
    private String resourceId;

    @TableField("TYPE_ID")
    private String typeId;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

    @TableField("UPDATE_TIME")
    private BigDecimal updateTime;


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

    public BigDecimal getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigDecimal createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(BigDecimal updateTime) {
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
