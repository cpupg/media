package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 查询资源的参数。
 *
 * @author sheepfly
 */
@Getter
@Setter
@ToString
public class ResourceParam extends TablePagination implements Serializable {
    private static final long serialVersionUID = 1L;
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
    /**
     * 和资源关联的专辑，只在selectModal中使用。
     */
    private String albumId;
    /**
     * 标签名称。
     */
    private String[] tagNames = {};
}
