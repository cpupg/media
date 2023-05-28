package com.sheepfly.media;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;

import java.util.Properties;

/**
 * 启动一个h2服务，可以在不启动应用的情况下操作数据库。
 *
 * @author wrote-code
 */
public class H2Server {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(ResourceUtil.getStream("application.properties"));
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("spring.datasource.druid.url"));
        dataSource.setUsername(properties.getProperty("spring.datasource.druid.username"));
        dataSource.setPassword(properties.getProperty("spring.datasource.druid.password"));
        dataSource.setDriverClassName(properties.getProperty("spring.datasource.druid.driver-class-name"));
        dataSource.init();
        JdbcConnectionPool pool = JdbcConnectionPool.create(dataSource);
        Server.startWebServer(pool.getConnection());
    }
}
