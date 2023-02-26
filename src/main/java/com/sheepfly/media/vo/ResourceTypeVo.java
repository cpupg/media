package com.sheepfly.media.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class ResourceTypeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 资源id
     */
    private ResourceVo resourceVo;

    /**
     * 类型id
     */
    private ResourceTypeMapVo resourceTypeMapVo;

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
        return "ResourceTypeVo{" +
                "id='" + id + '\'' +
                ", resourceVo=" + resourceVo +
                ", resourceTypeMapVo=" + resourceTypeMapVo +
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

    public ResourceVo getResourceVo() {
        return resourceVo;
    }

    public void setResourceVo(ResourceVo resourceVo) {
        this.resourceVo = resourceVo;
    }

    public ResourceTypeMapVo getResourceTypeMapVo() {
        return resourceTypeMapVo;
    }

    public void setResourceTypeMapVo(ResourceTypeMapVo resourceTypeMapVo) {
        this.resourceTypeMapVo = resourceTypeMapVo;
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
