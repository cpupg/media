package com.sheepfly.media.form.data;

import lombok.Data;

/**
 * 添加资源的表单。
 *
 * @author wrote-code
 * @date 2022.12.19
 * @since 0.0.1-SNAPSHOT
 */
@Data
public class ResourceData {
    /**
     * 作者id。
     */
    private String authorId;
    /**
     * 文件名。
     */
    private String filename;
    /**
     * 资源目录。
     */
    private String dir;
}
