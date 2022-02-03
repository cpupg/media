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
@TableName("RESOURCE_TYPE_MAP")
public class ResourceTypeMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("NAME")
    private String name;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ResourceTypeMap{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
