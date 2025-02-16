package com.sheepfly.media.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 排序sql。
 *
 * @author chen
 */
public class SorterSql {
    private static final String ASCEND = "ascend";
    private static final String DESCEND = "descend";
    private static final String SQL_PREFIX = "order by ";

    private final StringBuilder sqlBuilder = new StringBuilder(SQL_PREFIX);

    public SorterSql orderBy(String column, String order) {
        if (StringUtils.isBlank(order)) {
            return this;
        }
        sqlBuilder.append(column);
        if (ASCEND.equals(order)) {
            sqlBuilder.append(" asc,");
        } else if (DESCEND.equals(order)) {
            sqlBuilder.append(" desc,");
        }
        return this;
    }

    /**
     * 返回排序sql。
     *
     * @return sql。
     */
    public String getSql() {
        if (SQL_PREFIX.contentEquals(sqlBuilder)) {
            // 没有排序字段，返回空
            return null;
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        return sqlBuilder.toString();
    }
}
