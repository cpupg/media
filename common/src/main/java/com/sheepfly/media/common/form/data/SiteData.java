package com.sheepfly.media.common.form.data;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
}
