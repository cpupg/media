package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.entity.baseinterface.LogicDelete;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源和类型关联
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table(schema = "MEDIA", name = "RESOURCE_TYPE")
@Data
public class ResourceType implements Serializable, EntityInterface, LogicDelete {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 资源id
     */
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * 类型id
     */
    @Column(name = "TYPE_ID")
    private String typeId;

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
    public Integer getDeleteStatus() {
        return null;
    }

    @Override
    public void setDeleteStatus(Integer deleteStatus) {

    }
}
