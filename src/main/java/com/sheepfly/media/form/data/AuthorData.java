package com.sheepfly.media.form.data;

import lombok.Data;

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
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 注册站点ID
     */
    private String siteId;

    /**
     * 主页
     */
    private String homepage;
}
