package com.sheepfly.media.form.filter;

import com.sheepfly.media.vo.common.ProPaginationForm;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 查询作者用的参数。
 *
 * @author wrote-code
 */
public class AuthorFilter extends ProPaginationForm implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 用户在站点注册时的id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 注册站点ID
     */
    private String siteId;

    /**
     * 主页
     */
    private String homepage;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    @Override
    public String toString() {
        return "AuthorFilter{" + "id=" + id + ", userId=" + userId + ", username=" + username + ", siteId=" + siteId + ", homepage=" + homepage + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

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

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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
}
