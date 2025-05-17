package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

import java.util.List;

public class TagReferenceParam extends TablePagination {
    /**
     * 资源和标签关联关系标识。。
     */
    private String id;
    /**
     * 资源标识。
     */
    private String resourceId;
    /**
     * 资源列表。
     */
    private List<String> resourceIdList;
    /**
     * 标签标识。
     */
    private String tagId;
    /**
     * 标签名。
     */
    private String tagName;

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

    public List<String> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<String> resourceIdList) {
        this.resourceIdList = resourceIdList;
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

}
