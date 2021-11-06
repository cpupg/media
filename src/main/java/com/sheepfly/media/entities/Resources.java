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
public class Resources implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String fileName;

    private String createTime;

    private String updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", fileName=" + fileName +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
