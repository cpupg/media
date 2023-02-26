package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 资源所属专辑
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table(name = "RESOURCE_ALBUM")
public class ResourceAlbum implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private LocalDate updateTime;

    /**
     * 资源ID
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * 专辑ID
     */
    @Column(name = "ALBUM_ID")
    private String albumId;


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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "ResourceAlbum{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", resourceId=" + resourceId +
                ", albumId=" + albumId +
                "}";
    }
}
