package com.sheepfly.media.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 作者-资源关联。
 *
 * <p>一个资源对应一个作者，如果一个作者在多个站点上传了资源，则后面的作者和资源的对应关系会出现在
 * 这里。</p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("AUTHOR_RESOURCE")
public class AuthorResource implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 资源id。
     */
    @TableField("RESOURCE_ID")
    private String resourceId;
    /**
     * 作者id。
     */
    @TableField("AUTHOR_ID")
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
