package com.sheepfly.media.common.form.data;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class TagData extends TablePagination implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标签名。
     */
    @NotNull(message = "{entity.tag.name.notNull}")
    @Length(message = "{entity.tag.name.length}")
    private String name;
    /**
     * 逗号分隔的标签。
     */
    private String names;
    /**
     * 标签主键。
     */
    private String tagId;

    /**
     * 资源标识。
     */
    @NotNull(message = "{entity.tag.resourceId.notNull}")
    @Length(message = "{entity.tag.resourceId.length}")
    private String resourceId;
    /**
     * 逗号分隔的资源。
     */
    private String resourceIds;
    /**
     * 是否是评分标签。
     *
     * <p>评分标签只能是0-10的数字且只能有一个。</p>
     */
    private boolean rate;
    /**
     * 是否是收藏标签，name=收藏，只能有一个。
     */
    private boolean favourite;
}
