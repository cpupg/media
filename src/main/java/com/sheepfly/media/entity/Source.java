package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 资源/作者来源。
 *
 * <p>通常是网站。一个作者可以在多个网站注册账号，因此一个作者可以有用多个source。</p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("SOURCE")
public class Source implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 来源id。
     */
    @TableId("ID")
    private String id;
    /**
     * 网站主页。
     */
    @TableField("SITE")
    private String site;
    /**
     * 网站名称。
     */
    @TableField("NAME")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", site=" + site +
                ", name=" + name +
                "}";
    }
}
