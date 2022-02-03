package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-04
 */
@TableName("SITE")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("SITE_NAME")
    private String siteName;

    @TableField("URL")
    private String url;

    @TableField("CREATE_TIME")
    private BigDecimal createTime;

    @TableField("UPDATE_TIME")
    private BigDecimal updateTime;


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

    public BigDecimal getCreateTime() {
        return createTime;
    }

    public void setCreateTime(BigDecimal createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(BigDecimal updateTime) {
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
