package com.sheepfly.media.entity;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 站点
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Entity
@Table
@StaticMetamodel(Site.class)
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
    private LocalDate createTime;

    /**
     * 更细时间
     */
    @Column(name = "UPDATE_TIME")
    private LocalDate updateTime;


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

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", siteName=" + siteName +
                ", url=" + url +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
