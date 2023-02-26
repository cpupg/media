package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * 专辑
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table(name = "ALBUM")
public class Album implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    /**
     * 专辑
     */
    @Column(name = "ALBUM_NAME")
    private String albumName;

    /**
     * 专辑作者
     */
    @Column(name = "AUTHOR_ID")
    private String authorId;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private LocalDate createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private LocalDate updateTime;


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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
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
        return "Album{" +
                "id=" + id +
                ", albumName=" + albumName +
                ", authorId=" + authorId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
