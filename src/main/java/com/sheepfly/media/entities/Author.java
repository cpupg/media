package com.sheepfly.media.entities;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String createTime;

    private String updateTime;

    private String source;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name=" + name +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", source=" + source +
                "}";
    }
}
