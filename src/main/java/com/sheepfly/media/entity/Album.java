package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-04
 */
@TableName("ALBUM")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("ALBUM_NAME")
    private String albumName;

    @TableField("AUTHOR_ID")
    private String authorId;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

    @TableField("UPDATE_TIME")
    private BigDecimal updateTime;


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

    public BigDecimal getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigDecimal createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(BigDecimal updateTime) {
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
