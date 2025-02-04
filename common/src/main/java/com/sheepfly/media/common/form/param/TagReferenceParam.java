package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

public class TagReferenceParam extends TablePagination {
    private String id;
    private String resourceId;
    private String tagId;
    private String tagName;
    private boolean rate;
    private boolean favorite;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isRate() {
        return this.rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
