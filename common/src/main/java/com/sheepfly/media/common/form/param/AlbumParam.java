package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

import java.io.Serializable;

public class AlbumParam extends TablePagination implements Serializable {
    /**
     * 资源标识。
     */
    private String resourceId;
    /**
     * 专辑名称。
     */
    private String albumName;
    /**
     * 专辑标识。
     */
    private String albumId;
    /**
     * 查询时是否关联资源。
     */
    private boolean queryWithResource = false;

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public boolean isQueryWithResource() {
        return this.queryWithResource;
    }

    public void setQueryWithResource(boolean queryWithResource) {
        this.queryWithResource = queryWithResource;
    }
}
