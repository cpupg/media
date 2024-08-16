package com.sheepfly.media.common.form.data;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.AlbumVo;
import com.sheepfly.media.common.vo.TagVo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
@EqualsAndHashCode
public class ResourceData implements Serializable {
    /**
     * 批量更新内容toString的格式。
     */
    private static final String BATCH_UPDATE_FORMAT = "";
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
     * 目录代码。
     */
    private Long dirCode;
    /**
     * 作者id。
     */
    @NotNull(message = "{entity.resource.authorId.notNull}")
    @Length(max = 19, message = "{entity.resource.authorId.length}")
    private String authorId;
    /**
     * 封面文件id。
     */
    @Length(max = 19, message = "{entity.resource.coverId.length}")
    private String coverId;
    /**
     * 批量更新条件。
     */
    private TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition;
    /**
     * 删除的专辑。
     */
    private List<AlbumVo> deletedAlbums;
    /**
     * 新增的专辑。
     */
    private List<AlbumVo> addedAlbums;
    /**
     * 删除的标签。
     */
    private List<TagVo> deletedTags;
    /**
     * 新增的标签。
     */
    private List<TagVo> addedTags;

}
