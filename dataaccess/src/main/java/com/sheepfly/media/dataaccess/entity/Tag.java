package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "MEDIA.TAG")
public class Tag implements Serializable, LogicDelete, EntityInterface {

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
}
