package com.sheepfly.media.cli.task.impl;

import com.sheepfly.media.cli.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 转换目录任务。
 *
 * <p>将目录中的路径转换为dir_code并保存到临时表中。</p>
 */
@Component
@Slf4j
public class TransFormDirectoryTaskImpl implements Task {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;
    private String dropTemp;
    private String createTemp;
    private String queryDirectory;
    private String queryResource;
    private String insertTemp;
    private List<String> dirList;
    private List<Map<String, Object>> resourceList;

    public TransFormDirectoryTaskImpl() throws IOException {
        log.info("加载sql");
        ClassPathResource resource = new ClassPathResource("config/jdbc-template.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        dropTemp = properties.getProperty("dropTemp");
        createTemp = properties.getProperty("createTemp");
        queryDirectory = properties.getProperty("queryDirectory");
        queryResource = properties.getProperty("queryResource");
        insertTemp = properties.getProperty("insertTemp");
        log.info("sql加载完成");

    }

    @Override
    public void initializeTaskConfig() throws Exception {
        Task.super.initializeTaskConfig();
        log.info("删除临时表");
        jdbcTemplate.execute(dropTemp);
        log.info("创建临时表");
        jdbcTemplate.execute(createTemp);
        log.info("查询目录");
        dirList = jdbcTemplate.queryForList(queryDirectory, String.class);
        log.info("目录个数:{}", dirList.size());
    }

    @Override
    public void executeTask() throws Exception {
        log.info("开始转换任务");
    }

    private static class DirCode {
        public String id;
        public Long dirCode;
    }

    private static class Resource {
        public String id;
        public String filename;
        public String dir;
    }
}
