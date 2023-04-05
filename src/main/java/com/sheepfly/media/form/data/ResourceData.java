package com.sheepfly.media.form.data;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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
     * 文件名。
     */
    @NotNull(message = "{entity.resource.filename.notNull}")
    @Length(max = 90, message = "{entity.resource.filename.length}")
    private String filename;
    /**
     * 资源目录。
     */
    @NotNull(message = "{entity.resource.dir.notNull}")
    @Length(max = 900, message = "{entity.resource.dir.length}")
    private String dir;
    /**
     * 作者id。
     */
    @NotNull(message = "{entity.resource.authorId.notNull}")
    @Length(max = 19, message = "{entity.resource.authorId.length}")
    private String authorId;
}
