package com.sheepfly.media.common.vo;

import com.sheepfly.media.common.http.TablePagination;
import com.sheepfly.media.common.vo.constraintgroup.DeleteConstraint;
import com.sheepfly.media.common.vo.constraintgroup.InsertConstraint;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源下关联的收藏。
 */
public class ResourceCollectVo extends TablePagination implements Serializable {
    /**
     * 资源和收藏的关联id。
     */
    @NotBlank(groups = {DeleteConstraint.class}, message = "{entity.ResourceCollect.id.notEmpty}")
    private String resourceCollectId;

    /**
     * 资源主键id
     */
    @NotBlank(groups = {InsertConstraint.class}, message = "{entity.resourceCollect.resourceId.notBlank}")
    private String resourceId;

    /**
     * 收藏id
     */
    @NotBlank(groups = {InsertConstraint.class}, message = "{entity.resourceCollect.collectId.notBlank}")
    private String collectId;

    /**
     * 收藏夹名称。
     */
    private String collectName;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 创建时间
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

    public String getResourceCollectId() {
        return resourceCollectId;
    }

    public void setResourceCollectId(String resourceCollectId) {
        this.resourceCollectId = resourceCollectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
