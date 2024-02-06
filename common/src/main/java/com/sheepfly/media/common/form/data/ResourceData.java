package com.sheepfly.media.common.form.data;

import lombok.Getter;
import lombok.Setter;import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加资源的表单。
 *
 * @author wrote-code
 * @date 2022.12.19
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
public class ResourceData implements Serializable {
    /**
     * 资源标识。
     */
    @Length(max = 90)
    private String id;
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
