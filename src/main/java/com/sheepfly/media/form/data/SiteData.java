package com.sheepfly.media.form.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 站点
 * </p>
 */
@Data
public class SiteData implements Serializable {

    private static final long serialVersionUID = 1L;

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
