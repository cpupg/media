package com.sheepfly.media.common.constant;

/**
 * 系统中的常量。
 *
 * @author wrote-code
 * @since 0.0.1-SNAPSHOT
 */
public interface Constant {
    /**
     * sql通配符。
     */
    String SQL_LIKE = "%";
    /**
     * 空字符串。
     */
    String BLANK_STRING = "";
    String STANDARD_TIME = "yyyy-MM-dd HH:mm:ss.sss";
    /**
     * 实体已删除。
     */
    int DELETED = 1;
    /**
     * 实体未删除。
     */
    int NOT_DELETED = 0;

    String SEPERATOR = "/";
}
