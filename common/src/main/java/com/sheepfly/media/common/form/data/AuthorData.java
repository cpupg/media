package com.sheepfly.media.common.form.data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 创作人员
 * </p>
 *
 * @author wrote-code
 * @since 2022.10.18
 */
public class AuthorData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户在站点注册时的id
     */
    @Length(max = 90, message = "{entity.author.username.userId.length}")
    private String userId;

    /**
     * 用户名
     */
    @NotNull(message = "{entity.author.username.notNull}")
    @Length(max = 90, message = "{entity.author.username.length}")
    private String username;

    /**
     * 注册站点ID
     */
    @NotNull(message = "{entity.author.siteId.notNull}")
    @Length(max = 90, message = "{entity.id.maxLength}")
    private String siteId;

    /**
     * 主页
     */
    @URL(message = "{entity.author.homepage.url}")
    @Length(max = 512, message = "{entity.author.homepage.length}")
    private String homepage;

    public @Length(max = 90, message = "{entity.author.username.userId.length}") String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
