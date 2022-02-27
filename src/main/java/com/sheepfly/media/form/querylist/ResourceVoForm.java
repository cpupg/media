package com.sheepfly.media.form.querylist;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
public class ResourceVoForm implements Serializable {
    /**
     * 文件名。
     */
    private String filename;
    private int currentPage;
    private int pageSize;

    @Override
    public String toString() {
        return "ResourceVoForm{" +
                "filename='" + filename + '\'' +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
