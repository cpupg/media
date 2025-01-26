package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源专辑
 *
 * @TableName ALBUM_RESOURCE
 */
@Table(name = "ALBUM_RESOURCE", schema = "MEDIA")
@Entity
public class AlbumResource implements Serializable, LogicDelete, EntityInterface {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     *
     */
    @Column(name = "ALBUM_ID")
    private String albumId;

    /**
     *
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * 删除状态
     */
    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    public String getId() {
        return this.id;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public Integer getDeleteStatus() {
        return this.deleteStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Date getDeleteTime() {
        return this.deleteTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
