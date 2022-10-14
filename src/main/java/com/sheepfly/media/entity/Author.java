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
 * 创作人员
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Table
@Entity
public class Author implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
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
    @Column(name = "SITE")
    private String site;

    /**
     * 主页
     */
    @Column(name = "HOMEPAGE")
    private String homepage;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
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
        return "Author{" +
                "id=" + id +
                ", userId=" + userId +
                ", username=" + username +
                ", site=" + site +
                ", homepage=" + homepage +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
