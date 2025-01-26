package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class Site implements Serializable, EntityInterface, LogicDelete {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 网站名称
     */
    @Column(name = "SITE_NAME", nullable = false, length = 90)
    private String siteName;

    /**
     * 网站地址
     */
    @Column(name = "URL", nullable = false, length = 90)
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 更细时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 是否有效，用于逻辑删除。
     *
     * 0未删除1已删除。
     */
    @Column(name = "DELETE_STATUS", insertable = false)
    private Integer deleteStatus;

    public String getId() {
        return this.id;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public String getUrl() {
        return this.url;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Integer getDeleteStatus() {
        return this.deleteStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

}
