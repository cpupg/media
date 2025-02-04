package com.sheepfly.media.common.vo;

import java.io.Serializable;

/**
 * 专辑和资源关联关系。
 *
 * <p>馆连关系只关心专辑和资源，表现到vo里就是只需要关联标识，专辑id和名称，资源id和名称以及
 * 路径，不需要再嵌套vo。</p>
 *
 * @author wrote-code.
 */
public class AlbumResourceVo implements Serializable {
    private String id;
    private String albumId;
    private String albumName;
    private String resourceId;
    private String resourceName;
    private String resourceDir;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDir() {
        return this.resourceDir;
    }

    public void setResourceDir(String resourceDir) {
        this.resourceDir = resourceDir;
    }
}
