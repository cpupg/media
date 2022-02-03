package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-04
 */
@TableName("AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("USER_ID")
    private String userId;

    @TableField("USERNAME")
    private String username;

    @TableField("SITE")
    private String site;

    @TableField("HOMEPAGE")
    private String homepage;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

    @TableField("UPDATE_TIME")
    private BigDecimal updateTime;


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

    public BigDecimal getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigDecimal createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(BigDecimal updateTime) {
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
