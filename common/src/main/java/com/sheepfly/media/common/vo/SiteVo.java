package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class SiteVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更细时间
     */
    private Date updateTime;
}
