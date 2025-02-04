package com.sheepfly.media.common.form.data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 站点
 * </p>
 */
public class SiteData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网站名称
     */
    @NotNull(message = "{entity.site.siteName.notNull}")
    @Length(max = 90, message = "{entity.site.siteName.length}")
    private String siteName;

    /**
     * 网站地址
     */
    @URL(message = "{entity.site.url.illegal}")
    @NotNull(message = "{entity.site.url.notNull}")
    @Length(max = 90, message = "{entity.site.url.length}")
    private String url;

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
