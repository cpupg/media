package com.sheepfly.media.form.data;

/**
 * 添加资源的表单。
 *
 * @author wrote-code
 * @date 2022.12.19
 * @since 0.0.1-SNAPSHOT
 */
public class ResourceData {
    /**
     * 作者id。
     */
    private String authorId;
    /**
     * 文件名。
     */
    private String filename;
    /**
     * 资源目录。
     */
    private String dir;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
