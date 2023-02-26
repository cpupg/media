package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 资源类型映射
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table(name = "RESOURCE_TYPE_MAP")
public class ResourceTypeMap implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 父类型
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private LocalDate updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ResourceTypeMapRepository{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name=" + name +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
