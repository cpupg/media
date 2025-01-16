package com.sheepfly.media.common.form.data;

import java.io.Serializable;

public class BatchTag implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tags;
    private String resourceIds;

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
}
