package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 仓库表
 */
@Entity
@Table(name = "MEDIA.DIR_REPO", indexes = {@Index(name = "MEDIA.DIR_REPO_1", columnList = "DIR_CODE", unique = true)})
public class DirRepo implements Serializable, LogicDelete, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id。
     */
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    /**
     * 目录代码。
     */
    @Column(name = "DIR_CODE", nullable = false)
    private Long dirCode;

    /**
     * 仓库名称（不是目录名）。
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 全路径。
     */
    @Column(name = "PATH", nullable = false)
    private String path;

    /**
     * 删除状态。
     */
    @Column(name = "DELETE_STATUS", nullable = false)
    private Integer deleteStatus;

    /**
     * 创建时间。
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

    public String getId() {
        return this.id;
    }

    public Long getDirCode() {
        return this.dirCode;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
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

    public void setDirCode(Long dirCode) {
        this.dirCode = dirCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
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
