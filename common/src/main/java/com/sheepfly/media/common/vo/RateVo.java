package com.sheepfly.media.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 评分视图。
 *
 * @author chen
 */
public class RateVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评分主键标识。
     */
    private String rateId;
    /**
     * 资源标识
     */
    private String resourceId;
    /**
     * 评分
     */
    private Integer rate;
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

    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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
}
