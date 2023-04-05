package com.sheepfly.media.form.filter;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
@Data
public class ResourceFilter implements Serializable {
    /**
     * 文件名。
     */
    private String filename;
    private int currentPage;
    private int pageSize;
}
