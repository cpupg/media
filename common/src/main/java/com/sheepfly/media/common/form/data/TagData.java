package com.sheepfly.media.common.form.data;

import com.sheepfly.media.common.http.TablePagination;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    public @NotNull(message = "{entity.tag.name.notNull}") @Length(message = "{entity.tag.name.length}") String getName() {
        return this.name;
    }

    public void setName(@NotNull(message = "{entity.tag.name.notNull}") @Length(message = "{entity.tag.name.length}") String name) {
        this.name = name;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public @NotNull(message = "{entity.tag.resourceId.notNull}") @Length(message = "{entity.tag.resourceId.length}") String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(@NotNull(message = "{entity.tag.resourceId.notNull}") @Length(message = "{entity.tag.resourceId.length}") String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public boolean isRate() {
        return this.rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public boolean isFavourite() {
        return this.favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
