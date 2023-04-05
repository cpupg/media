package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String id;

    /**
     * 文件名
     */
    @Column(name = "FILENAME", nullable = false, length = 90)
    private String filename;

    /**
     * 资源目录
     */
    @Column(name = "DIR", length = 900, nullable = false)
    private String dir;

    /**
     * 作者id
     */
    @Column(name = "AUTHOR_ID", nullable = false, length = 19)
    private String authorId;

    /**
     * 专辑id
     */
    @Column(name = "ALBUM_ID", length = 19)
    private String albumId;

    /**
     * 创建时间。
     *
     * <p>创建时间是资源生成时间。对视频和照片来说，创建时间就是拍摄时间。如果是新闻，则创建时间
     * 是新闻发生时间，而不是发稿时间，不管是视频稿还是文字稿。</p>
     *
     * <p>默认等于保存时间。</p>
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 保存时间（入库时间）。
     *
     * <p>资源保存到数据库的时间。</p>
     */
    @Column(name = "SAVE_TIME")
    private Date saveTime;
}
