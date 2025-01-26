package com.sheepfly.media.common.form.data;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.AlbumVo;
import com.sheepfly.media.common.vo.TagVo;
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

    public @Length(max = 90) String getId() {
        return this.id;
    }

    public void setId(@Length(max = 90) String id) {
        this.id = id;
    }

    public @NotNull(message = "{entity.resource.filename.notNull}") @Length(max = 90, message = "{entity.resource.filename.length}") String getFilename() {
        return this.filename;
    }

    public void setFilename(@NotNull(message = "{entity.resource.filename.notNull}") @Length(max = 90, message = "{entity.resource.filename.length}") String filename) {
        this.filename = filename;
    }

    public @NotNull(message = "{entity.resource.dir.notNull}") @Length(max = 900, message = "{entity.resource.dir.length}") String getDir() {
        return this.dir;
    }

    public void setDir(@NotNull(message = "{entity.resource.dir.notNull}") @Length(max = 900, message = "{entity.resource.dir.length}") String dir) {
        this.dir = dir;
    }

    public Long getDirCode() {
        return this.dirCode;
    }

    public void setDirCode(Long dirCode) {
        this.dirCode = dirCode;
    }

    public @NotNull(message = "{entity.resource.authorId.notNull}") @Length(max = 19, message = "{entity.resource.authorId.length}") String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(@NotNull(message = "{entity.resource.authorId.notNull}") @Length(max = 19, message = "{entity.resource.authorId.length}") String authorId) {
        this.authorId = authorId;
    }

    public @Length(max = 19, message = "{entity.resource.coverId.length}") String getCoverId() {
        return this.coverId;
    }

    public void setCoverId(@Length(max = 19, message = "{entity.resource.coverId.length}") String coverId) {
        this.coverId = coverId;
    }

    public TableRequest<ResourceFilter, ResourceParam, ResourceSort> getCondition() {
        return this.condition;
    }

    public void setCondition(TableRequest<ResourceFilter, ResourceParam, ResourceSort> condition) {
        this.condition = condition;
    }

    public List<AlbumVo> getDeletedAlbums() {
        return this.deletedAlbums;
    }

    public void setDeletedAlbums(List<AlbumVo> deletedAlbums) {
        this.deletedAlbums = deletedAlbums;
    }

    public List<AlbumVo> getAddedAlbums() {
        return this.addedAlbums;
    }

    public void setAddedAlbums(List<AlbumVo> addedAlbums) {
        this.addedAlbums = addedAlbums;
    }

    public List<TagVo> getDeletedTags() {
        return this.deletedTags;
    }

    public void setDeletedTags(List<TagVo> deletedTags) {
        this.deletedTags = deletedTags;
    }

    public List<TagVo> getAddedTags() {
        return this.addedTags;
    }

    public void setAddedTags(List<TagVo> addedTags) {
        this.addedTags = addedTags;
    }

}
