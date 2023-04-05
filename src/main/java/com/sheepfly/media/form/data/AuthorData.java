package com.sheepfly.media.form.data;

import lombok.Data;
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
@Data
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
    @Length(max = 90, message = "{entity.author.homepage.length}")
    private String homepage;
}
