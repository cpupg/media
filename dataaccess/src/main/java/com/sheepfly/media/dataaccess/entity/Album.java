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
 * 专辑
 *
 * @TableName ALBUM
 */
@Table(name = "ALBUM", schema = "MEDIA")
@Getter
@Entity
@ToString
@Setter
public class Album implements Serializable, EntityInterface, LogicDelete {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 专辑名称
     */
    @Column(name = "NAME")
    private String name;

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

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;
}
