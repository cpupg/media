package com.sheepfly.media.entities;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
public class Source implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String site;

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
