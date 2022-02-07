package com.sheepfly.media.vo.common;

import java.util.List;

/**
 * 分页对象。
 *
 * @author sheepfly
 */
public class PageObject<T> {
    /**
     * 当前页。
     */
    private Integer currentPage;
    /**
     * 页面容量。
     */
    private Integer pageSize;
    /**
     * 总页数。
     */
    private Integer totalPage;
    /**
     * 总条数。
     */
    private Integer totalRecords;
    /**
     * 数据列表。
     */
    private List<T> dataList;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
