package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源关联的收藏
 *
 * @TableName RESOURCE_COLLECT
 */
@Entity
@Table(name = "RESOURCE_COLLECT", schema = "MEDIA")
public class ResourceCollect implements Serializable, EntityInterface {
    private static final long serialVersionUID = 1L;
    /**
     * 关联id
     */
    @Id
    @Column(name = "RESOURCE_COLLECT_ID")
    private String resourceCollectId;

    /**
     * 资源主键id
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * 收藏id
     */
    @Column(name = "COLLECT_ID")
    private String collectId;

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
     * 更新时间。
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 删除时间。
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    /**
     * 关联id
     */
    public String getResourceCollectId() {
        return resourceCollectId;
    }

    /**
     * 关联id
     */
    public void setResourceCollectId(String resourceCollectId) {
        this.resourceCollectId = resourceCollectId;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public void setId(String id) {

    }

    /**
     * 资源主键id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 资源主键id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 收藏id
     */
    public String getCollectId() {
        return collectId;
    }

    /**
     * 收藏id
     */
    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    /**
     * 删除状态
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 删除状态
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
