package com.sheepfly.media.form.data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 站点
 * </p>
 */
public class SiteData implements Serializable {

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String toString() {
        return "Site{" + "id=" + id + ", siteName=" + siteName + ", url=" + url + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
    }
}
