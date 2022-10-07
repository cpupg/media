package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@TableName("RESOURCE")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 文件名
     */
    @TableField("FILENAME")
    private String filename;

    /**
     * 资源目录
     */
    @TableField("DIR")
    private String dir;

    /**
     * 作者id
     */
    @TableField("AUTHOR_ID")
    private String authorId;

    /**
     * 专辑id
     */
    @TableField("ALBUM_ID")
    private String albumId;

    /**
     * 创建时间。
     *
     * <p>创建时间是资源生成时间。对视频和照片来说，创建时间就是拍摄时间。如果是新闻，则创建时间
     * 是新闻发生时间，而不是发稿时间，不管是视频稿还是文字稿。</p>
     */
    @TableField("CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDate updateTime;

    /**
     * 保存时间（入库时间）。
     */
    @TableField("SAVE_TIME")
    private LocalDate saveTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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

    public LocalDate getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(LocalDate saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", dir='" + dir + '\'' +
                ", authorId='" + authorId + '\'' +
                ", albumId='" + albumId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", saveTime=" + saveTime +
                '}';
    }
}
