package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 *
 * @TableName COLLECT
 */
@Entity
@Table(name = "COLLECT", schema = "MEDIA")
public class Collect implements Serializable, EntityInterface {
    private static final long serialVersionUID = 1L;
    /**
     * 收藏id
     */
    @Id
    @Column(name = "COLLECT_ID")
    private String collectId;

    /**
     * 收藏名称
     */
    @Column(name = "COLLECT_NAME")
    private String collectName;

    /**
     * 删除状态
     */
    @Column(name = "DELETE_STATUS", nullable = false)
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME", nullable = false)
    private Date updateTime;

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME", nullable = false)
    private Date deleteTime;


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


    @Override
    public String getId() {
        return this.collectId;
    }

    @Override
    public void setId(String id) {
        this.collectId = id;
    }

    /**
     * 收藏名称
     */
    public String getCollectName() {
        return collectName;
    }

    /**
     * 收藏名称
     */
    public void setCollectName(String collectName) {
        this.collectName = collectName;
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

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 删除时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 删除时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
