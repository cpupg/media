package com.sheepfly.media.common.http;

import java.io.Serializable;
import java.util.List;

/**
 * ProComponent的Request回调参数。
 *
 * @param <F> 过滤参数。
 * @param <P> 查询参数。
 * @param <S> 排序参数。
 *
 * @author sheepfly
 */
public class TableRequest<F, P, S> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 表头过滤参数。
     */
    private F filter;
    /**
     * 表格上方搜索框。
     */
    private P params;
    /**
     * 表头排序参数。
     */
    private S sorter;
    /**
     * 排序语句。
     */
    private String orderBy;
    /**
     * 勾选的id。
     */
    private List<String> idList;

    public TableRequest() {
    }

    public TableRequest(F filter, P params, S sort) {
        this.filter = filter;
        this.params = params;
        this.sorter = sort;
    }

    public F getFilter() {
        return filter;
    }

    public void setFilter(F filter) {
        this.filter = filter;
    }

    public P getParams() {
        return params;
    }

    public void setParams(P params) {
        this.params = params;
    }

    public S getSorter() {
        return sorter;
    }

    public void setSorter(S sort) {
        this.sorter = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}
