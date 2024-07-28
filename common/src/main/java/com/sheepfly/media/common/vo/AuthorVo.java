package com.sheepfly.media.common.vo;


import java.io.Serializable;
import java.util.Date;

public class AuthorVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private SiteVo siteVo;

    /**
     * 主页
     */
    private String homepage;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "AuthorVo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", site=" + siteVo +
                ", homepage='" + homepage + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
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

    public SiteVo getSiteVo() {
        return siteVo;
    }

    public void setSiteVo(SiteVo siteVo) {
        this.siteVo = siteVo;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
