package com.sheepfly.media.vo.common;

/**
 * ProTable请求分页参数。
 *
 * <p>报表页面查询Form可以继承这个类。</p>
 *
 * @author sheepfly
 */
public class ProPaginationForm {
    private int pageSize;
    private int current;

    @Override
    public String toString() {
        return "ProPaginationForm{" + "pageSize=" + pageSize + ", current=" + current + '}';
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
