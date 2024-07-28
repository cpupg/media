package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源。
 *
 * @author sheepfly
 */
@Getter
@Setter
@ToString
public class ResourceVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private String id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 资源目录
     */
    private String dir;
    /**
     * 目录代码。
     */
    private Long dirCode;

    /**
     * 作者。
     */
    private AuthorVo authorVo;

    /**
     * 专辑。
     */
    private AlbumVo albumVo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 资源拥有的标签。
     */
    private List<TagReferenceVo> tagReferenceVoList;
    /**
     * 标签数量。
     */
    private Long tagCount;
    /**
     * 收藏。
     */
    private boolean favorite;
    /**
     * 评分。
     */
    private int rate;
    /**
     * 封面文件id。
     */
    private String coverId;
    /**
     * 和资源关联的专辑id，只在ResourceSelectModal中使用。
     */
    private String albumId;
}
