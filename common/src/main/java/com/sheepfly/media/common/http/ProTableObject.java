package com.sheepfly.media.common.http;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页对象。适配前台的ProTable。
 *
 * @author sheepfly
 */
@Getter
@Setter
public class ProTableObject<T> {
    private static final String QUERY_SUCCESS = "查询数据成功";
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
    private Long total;
    /**
     * 数据列表。
     */
    private List<T> data;
    private String message;
    private boolean success;

    public ProTableObject() {
    }

    public ProTableObject(Long total, List<T> data) {
        this.total = total;
        this.data = data;
        this.success = true;
        this.message = QUERY_SUCCESS;
    }

    public ProTableObject(String message) {
        this.success = false;
        this.message = message;
    }

    /**
     * 创建分页查询结果。
     *
     * @param list 查询结果。
     * @param total 数据量。
     * @param <T> 数据类型。
     *
     * @return 查询结果。
     */
    public static <T> ProTableObject success(List<T> list, Long total) {
        return new ProTableObject<>(total, list);
    }

    public static <T> ProTableObject fail(String message) {
        return new ProTableObject(message);
    }
}
