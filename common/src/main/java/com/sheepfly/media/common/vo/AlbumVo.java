package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑。
 *
 * @author wrote-code
 */
public class AlbumVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 专辑名称
     */
    private String name;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 封面文件id。
     */
    private String coverId;
    /**
     * 和专辑关联的资源id。
     *
     * <p>此字段是为了在选择专辑时高亮已选专辑，其他时候不用。</p>
     */
    private String resourceId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCoverId() {
        return this.coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
