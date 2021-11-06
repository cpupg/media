package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 作者。
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    /**
     * 用户名。
     */
    @TableField("USER_NAME")
    private String userName;
    /**
     * 创建时间。
     */
    @TableField("CREATE_TIME")
    private String createTime;
    /**
     * 更新时间。
     */
    @TableField("UPDATE_TIME")
    private String updateTime;
    /**
     * 来源。
     */
    @TableField("SOURCE")
    private String source;
    /**
     * 用户在网站注册的id，如果网站只显示用户名，则使用用户名。
     */
    @TableField("USER_ID")
    private String userId;
    /**
     * 用户主页。
     */
    @TableField("USER_SPACE")
    private String userSpace;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSpace() {
        return userSpace;
    }

    public void setUserSpace(String userSpace) {
        this.userSpace = userSpace;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", source='" + source + '\'' +
                ", userId='" + userId + '\'' +
                ", userSpace='" + userSpace + '\'' +
                '}';
    }
}
