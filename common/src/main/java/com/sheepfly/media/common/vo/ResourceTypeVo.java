package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
