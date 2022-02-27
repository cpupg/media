package com.sheepfly.media.vo;

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
    private AuthorVo authorVo;

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
                ", authorVo=" + authorVo +
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

    public AuthorVo getAuthorVo() {
        return authorVo;
    }

    public void setAuthorVo(AuthorVo authorVo) {
        this.authorVo = authorVo;
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
