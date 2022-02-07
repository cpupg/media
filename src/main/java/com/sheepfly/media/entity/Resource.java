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
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDate updateTime;


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

    @Override
    public String toString() {
        return "Resource{" +
        "id=" + id +
        ", filename=" + filename +
        ", dir=" + dir +
        ", authorId=" + authorId +
        ", albumId=" + albumId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
