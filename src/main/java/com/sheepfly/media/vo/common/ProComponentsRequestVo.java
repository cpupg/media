package com.sheepfly.media.vo.common;

/**
 * ProComponent的Request回调参数。
 *
 * @param <F> 过滤参数。
 * @param <P> 查询参数。
 * @param <S> 排序参数。
 *
 * @author sheepfly
 */
public class ProComponentsRequestVo<F, P, S> {
    private F filter;
    private P params;
    private S sort;

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

    public S getSort() {
        return sort;
    }

    public void setSort(S sort) {
        this.sort = sort;
    }
}
