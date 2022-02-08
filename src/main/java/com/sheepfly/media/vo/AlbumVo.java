package com.sheepfly.media.vo;

import com.sheepfly.media.entity.Author;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 专辑。
 *
 * @author sheepfly
 */
public class AlbumVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 专辑
     */
    private String albumName;

    /**
     * 专辑作者
     */
    private Author author;

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
        return "AlbumVo{" +
                "id='" + id + '\'' +
                ", albumName='" + albumName + '\'' +
                ", author=" + author +
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

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
