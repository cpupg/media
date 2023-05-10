package com.sheepfly.media.form.filter;

import com.sheepfly.media.vo.common.ProPaginationForm;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
@Data
public class ResourceFilter extends ProPaginationForm implements Serializable {
    /**
     * 文件名。
     */
    private String filename;
}
