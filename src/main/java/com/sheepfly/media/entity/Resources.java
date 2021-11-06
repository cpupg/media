package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 资源。
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("RESOURCES")
public class Resources implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("FILE_NAME")
    private String fileName;
    @TableField("CREATE_TIME")
    private String createTime;
    @TableField("UPDATE_TIME")
    private String updateTime;
    @TableField("AUTHOR")
    private String author;
    @TableField("FILE_DIR")
    private String fileDir;
    @TableField("SCORE")
    private Integer score;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", author='" + author + '\'' +
                ", fileDir='" + fileDir + '\'' +
                ", score=" + score +
                '}';
    }
}
