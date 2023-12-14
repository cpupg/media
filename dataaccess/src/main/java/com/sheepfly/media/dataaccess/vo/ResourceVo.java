package com.sheepfly.media.dataaccess.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源。
 * @author sheepfly
 */
@Getter
@Setter
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

    @Override
    public String toString() {
        return "ResourceVo{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", dir='" + dir + '\'' +
                ", author=" + authorVo +
                ", albumVo=" + albumVo +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
