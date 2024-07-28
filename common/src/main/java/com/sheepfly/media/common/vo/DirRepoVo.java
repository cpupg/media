package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class DirRepoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id。
     */
    private String id;

    /**
     * 目录代码。
     */
    private Long dirCode;

    /**
     * 仓库名称（不是目录名）。
     */
    private String name;

    /**
     * 全路径。
     */
    private String path;

    /**
     * 删除状态。
     */
    private Integer deleteStatus;

    /**
     * 创建时间。
     */
    private Date createTime;

    /**
     * 更新时间。
     */
    private Date updateTime;

    /**
     * 删除时间。
     */
    private Date deleteTime;
}
