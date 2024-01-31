package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.ProPaginationForm;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
@Data
public class ResourceParam extends ProPaginationForm implements Serializable {
    /**
     * 文件名。
     */
    private String filename;
    /**
     * 文件目录。
     */
    private String dir;
    /**
     * 作者名。
     */
    private String authorName;
    /**
     * 作者唯一标识。
     */
    private String authorId;
}
