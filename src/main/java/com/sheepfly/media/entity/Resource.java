package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Data
@Table(schema = "MEDIA", name = "RESOURCE")
public class Resource implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @NotNull
    @Length(max = 19)
    private String id;

    /**
     * 文件名
     */
    @Column(name = "FILENAME")
    @NotNull
    @Length(max = 90)
    private String filename;

    /**
     * 资源目录
     */
    @Column(name = "DIR")
    @NotNull
    @Length(min = 0, max = 900)
    private String dir;

    /**
     * 作者id
     */
    @Column(name = "AUTHOR_ID")
    @NotNull
    @Length(max = 19)
    private String authorId;

    /**
     * 专辑id
     */
    @Column(name = "ALBUM_ID")
    @Length(max = 19)
    private String albumId;

    /**
     * 创建时间。
     *
     * <p>创建时间是资源生成时间。对视频和照片来说，创建时间就是拍摄时间。如果是新闻，则创建时间
     * 是新闻发生时间，而不是发稿时间，不管是视频稿还是文字稿。</p>
     */
    @Column(name = "CREATE_TIME")
    @NotNull
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 保存时间（入库时间）。
     */
    @Column(name = "SAVE_TIME")
    private Date saveTime;
}
