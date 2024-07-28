package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class TagVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;
}
