package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

public class ResourceTypeMapVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 父类型
     */
    private ResourceTypeMapVo parentResourceTypeMapVo;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResourceTypeMapVo getParentResourceTypeMapVo() {
        return parentResourceTypeMapVo;
    }

    public void setParentResourceTypeMapVo(ResourceTypeMapVo parentResourceTypeMapVo) {
        this.parentResourceTypeMapVo = parentResourceTypeMapVo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
