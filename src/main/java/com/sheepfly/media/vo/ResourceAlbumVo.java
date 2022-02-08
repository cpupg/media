package com.sheepfly.media.vo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 资源和专辑关联关系。
 */
public class ResourceAlbumVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 资源ID
     */
    private ResourceVo resourceVo;

    /**
     * 专辑ID
     */
    private AlbumVo albumVo;

    @Override
    public String toString() {
        return "ResourceAlbumVo{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", resourceVo=" + resourceVo +
                ", albumVo=" + albumVo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ResourceVo getResourceVo() {
        return resourceVo;
    }

    public void setResourceVo(ResourceVo resourceVo) {
        this.resourceVo = resourceVo;
    }

    public AlbumVo getAlbumVo() {
        return albumVo;
    }

    public void setAlbumVo(AlbumVo albumVo) {
        this.albumVo = albumVo;
    }
}
