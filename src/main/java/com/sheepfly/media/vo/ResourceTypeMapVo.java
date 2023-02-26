package com.sheepfly.media.vo;

import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    @Override
    public String toString() {
        return "ResourceTypeMapVo{" +
                "id='" + id + '\'' +
                ", parentResourceTypeMapVo=" + parentResourceTypeMapVo +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

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
}
