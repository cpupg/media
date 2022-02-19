package com.sheepfly.media.vo.common;

import java.util.List;

/**
 * 分页对象。适配前台的ProTable。
 *
 * @author sheepfly
 */
public class ProTableObject<T> {
    /**
     * 当前页。
     */
    private Integer page;
    /**
     * 页面容量。
     */
    private Integer pageSize;
    /**
     * 总条数。
     */
    private Integer total;
    /**
     * 数据列表。
     */
    private List<T> data;
    private String errorMessage;

    public ProTableObject() {
    }

    public ProTableObject(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
