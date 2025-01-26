package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class Author implements Serializable, EntityInterface, LogicDelete {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 用户在站点注册时的id
     */
    @Column(name = "USER_ID", length = 90)
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "USERNAME", nullable = false, length = 90)
    private String username;

    /**
     * 注册站点ID
     */
    @Column(name = "SITE_ID", nullable = false, length = 19)
    private String siteId;

    /**
     * 主页
     */
    @Column(name = "HOMEPAGE", length = 90)
    private String homepage;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 是否有效，用于逻辑删除。
     *
     * 0未删除1已删除。
     */
    @Column(name = "DELETE_STATUS", insertable = false)
    private Integer deleteStatus;

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Integer getDeleteStatus() {
        return this.deleteStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
