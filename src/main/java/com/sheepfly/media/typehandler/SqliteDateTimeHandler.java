package com.sheepfly.media.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 * sqlite时间时间处理器。
 *
 * <p>目前已经将数据库替换为h2，此处理器会在下个版本删除。</p>
 *
 * @author wrote-code
 * @since 0.0.1-SNAPSHOT
 * @deprecated 数据库已经改为h2
 */
public class SqliteDateTimeHandler implements TypeHandler<Time> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Time parameter, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public Time getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Time getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Time getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
