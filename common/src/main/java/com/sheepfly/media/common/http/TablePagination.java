package com.sheepfly.media.common.http;

import java.io.Serializable;

/**
 * ProTable请求分页参数。
 *
 * <p>报表页面查询Form可以继承这个类。</p>
 *
 * <p>分页参数增加了默认值，防止全表查询。</p>
 *
 * @param <T> 查询参数，用来做过滤，搜索和排序。
 *
 * @author sheepfly
 */
public class TablePagination  implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageSize = 20;
    private int current = 1;

    @Override
    public String toString() {
        return "TablePagination{" + "pageSize=" + pageSize + ", current=" + current + '}';
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
