package com.sheepfly.media.dataaccess.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class TagReferenceVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 标签id
     */
    private TagVo tagVo;

    /**
     * 引用类型1:资源
     */
    private Integer referenceType;
    /**
     * 引用时间。
     */
    private Date referTime;
}
