package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 评分
 *
 * @author chen
 */
@Table(schema = "media", name = "RATE")
public class Rate implements Serializable, EntityInterface {
    private static final long serialVersionUID = 1L;
    /**
     * 评分主键标识。
     */
    @Id
    @Column(name = "RATE_ID")
    private String rateId;
    /**
     * 资源标识
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;
    /**
     * 评分
     */
    @Column(name = "RATE")
    private Integer rate;
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

    @Override
    public String getId() {
        return rateId;
    }

    @Override
    public void setId(String id) {
        this.rateId = id;
    }

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

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
