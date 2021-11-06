package com.sheepfly.media.entities;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("AUTHOR_RESOURCE")
public class AuthorResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String resourceId;

    private String authorId;


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "AuthorResource{" +
                "resourceId=" + resourceId +
                ", authorId=" + authorId +
                "}";
    }
}
