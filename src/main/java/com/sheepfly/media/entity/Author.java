package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 创作人员
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Table(schema = "MEDIA", name = "AUTHOR")
@Entity
@Data
public class Author implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @NotNull
    private String id;

    /**
     * 用户在站点注册时的id
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 注册站点ID
     */
    @Column(name = "SITE_ID", nullable = false)
    private String siteId;

    /**
     * 主页
     */
    @Column(name = "HOMEPAGE")
    private String homepage;

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
}
