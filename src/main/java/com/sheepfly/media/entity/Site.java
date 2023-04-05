package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 站点
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table(schema = "MEDIA", name = "SITE")
@Data
public class Site implements Serializable, EntityInterface {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 网站名称
     */
    @NotNull(message = "{com.sheepfly.media.entity.site.siteName.notNull}")
    @Column(name = "SITE_NAME", nullable = false)
    private String siteName;

    /**
     * 网站地址
     */
    @URL(message = "{com.sheepfly.media.entity.site.url.illegal}")
    @NotNull(message = "{com.sheepfly.media.entity.site.url.notNull}")
    @Column(name = "URL", nullable = false)
    private String url;

    /**
     * 创建时间
     */
    @NotNull(message = "{com.sheepfly.media.entity.site.createTime.notNull}")
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 更细时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
}
