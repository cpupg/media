package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑。
 *
 * @author wrote-code
 */
@Getter
@Setter
@ToString
public class AlbumVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 专辑名称
     */
    private String name;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 封面文件id。
     */
    private String coverId;
    /**
     * 和专辑关联的资源id。
     *
     * <p>此字段是为了在选择专辑时高亮已选专辑，其他时候不用。</p>
     */
    private String resourceId;
}
