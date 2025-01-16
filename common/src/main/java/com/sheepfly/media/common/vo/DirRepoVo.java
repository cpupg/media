package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

public class DirRepoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id。
     */
    private String id;

    /**
     * 目录代码。
     */
    private Long dirCode;

    /**
     * 仓库名称（不是目录名）。
     */
    private String name;

    /**
     * 全路径。
     */
    private String path;

    /**
     * 删除状态。
     */
    private Integer deleteStatus;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 更新时间。
     */
    private Date updateTime;

    /**
     * 删除时间。
     */
    private Date deleteTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDirCode() {
        return this.dirCode;
    }

    public void setDirCode(Long dirCode) {
        this.dirCode = dirCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDeleteStatus() {
        return this.deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
