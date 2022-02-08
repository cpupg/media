package com.sheepfly.media.vo;

import com.sheepfly.media.entity.Site;

import java.io.Serializable;
import java.time.LocalDate;

public class AuthorVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

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
    private Site site;

    /**
     * 主页
     */
    private String homepage;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;
}
