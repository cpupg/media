package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 资源。
 *
 * <p>资源的作者字段是第一次下载资源时的上传用户。如果发现该用户在其他站点同步上传了资源，则后续站点
 * 的资源会写入AuthorResource中。</p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("RESOURCES")
public class Resources implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 资源id。
     */
    @TableId("ID")
    private String id;
    /**
     * 资源文件名。
     */
    @TableField("FILE_NAME")
    private String fileName;
    /**
     * 创建时间。
     */
    @TableField("CREATE_TIME")
    private String createTime;
    /**
     * 更新时间。移动、改变分类和作者都会视为更新资源。
     */
    @TableField("UPDATE_TIME")
    private String updateTime;
    /**
     * 作者。
     */
    @TableField("AUTHOR")
    private String author;
    /**
     * 资源在本地的存储目录。
     */
    @TableField("FILE_DIR")
    private String fileDir;
    /**
     * 评分，区间0到10。
     */
    @TableField("SCORE")
    private Integer score;
    /**
     * 资源链接。
     */
    private String url;
    /**
     * 下载连接。
     */
    private String downloadUrl;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
