package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
public class ResourceParam extends TablePagination implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文件名。
     */
    private String filename;
    /**
     * 精确路径。
     */
    private boolean accurateDir = false;
    /**
     * 文件目录。
     */
    private String dir;
    /**
     * 作者名。
     */
    private String authorName;
    /**
     * 作者唯一标识。
     */
    private String authorId;
    /**
     * 和资源关联的专辑，只在selectModal中使用。
     */
    private String albumId;
    /**
     * 标签名称。
     */
    private String[] tagNames = {};
    /**
     * 是否只查询资源表，默认否。
     *
     * <p>设置为true可以不进行1+n查询，用来在只需要资源表的场景中避免查询和资源表关联的其他
     * 表。</p>
     */
    private boolean resourceOnly = false;

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isAccurateDir() {
        return this.accurateDir;
    }

    public void setAccurateDir(boolean accurateDir) {
        this.accurateDir = accurateDir;
    }

    public String getDir() {
        return this.dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String[] getTagNames() {
        return this.tagNames;
    }

    public void setTagNames(String[] tagNames) {
        this.tagNames = tagNames;
    }

    public boolean isResourceOnly() {
        return this.resourceOnly;
    }

    public void setResourceOnly(boolean resourceOnly) {
        this.resourceOnly = resourceOnly;
    }
}
