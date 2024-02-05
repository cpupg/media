package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询作者用的参数。
 *
 * @author wrote-code
 */
@Getter
@Setter
@ToString
public class AuthorParam extends TablePagination implements Serializable {
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
    private String siteId;

    /**
     * 主页
     */
    private String homepage;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
