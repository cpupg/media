package com.sheepfly.media.common.vo;

import java.util.Date;

/**
 * 资源下关联的收藏。
 */
public class ResourceCollectVo {
    /**
     * 资源和收藏的关联id。
     */
    private String resourceCollectId;

    /**
     * 资源主键id
     */
    private String resourceId;

    /**
     * 收藏id
     */
    private String collectId;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getResourceCollectId() {
        return resourceCollectId;
    }

    public void setResourceCollectId(String resourceCollectId) {
        this.resourceCollectId = resourceCollectId;
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
}
