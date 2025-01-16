package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.common.util.BeanCopier;
import com.sheepfly.media.dataaccess.entity.baseinterface.BaseBean;
import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 */
@Entity
@Table(name = "MEDIA.TAG")
public class Tag implements Serializable, LogicDelete, EntityInterface, BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    @Override
    public void copyFrom(Object source) {
        BeanCopier.copyFrom(source, this);
    }

    @Override
    public void copyTo(Object target) {
        BeanCopier.copyTo(this, target);
    }

    @Override
    public Integer getDeleteStatus() {
        // 应付语法检查，没有实际用途
        return null;
    }

    @Override
    public void setDeleteStatus(Integer deleteStatus) {
        // 应付语法检查，没有实际用途
    }

    @Override
    public Date getUpdateTime() {
        // 应付语法检查，没有实际用途
        return null;
    }

    @Override
    public void setUpdateTime(Date date) {
        // 应付语法检查，没有实际用途
    }

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

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}
