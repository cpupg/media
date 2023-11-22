package com.sheepfly.media.cli.task.impl;

import com.sheepfly.media.cli.task.Task;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Directory_;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.service.base.DirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private DirectoryService service;
    @Autowired
    private DirectoryRepository repository;
    private String dropTemp;
    private String createTemp;
    private String queryDirectory;
    private String queryResource;
    private String insertTemp;
    private List<String> dirList;
    private List<Directory> completedDirList;
    private List<String> uncompletedDirList;

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
        completedDirList = new ArrayList<>();
        uncompletedDirList = new ArrayList<>();

    }

    @Override
    public void initializeTaskConfig() throws Exception {
        Task.super.initializeTaskConfig();
        try {
            log.info("删除临时表");
            jdbcTemplate.execute(dropTemp);
        } catch (Exception e) {
            // 删除失败说明没有表，不需要处理异常
            log.error("删除临时表失败", e);
        }
        log.info("创建临时表");
        // 创建失败要抛出异常
        jdbcTemplate.execute(createTemp);
        log.info("查询目录");
        dirList = jdbcTemplate.queryForList(queryDirectory, String.class);
        log.info("目录个数:{}", dirList.size());
    }

    @Override
    public void executeTask() throws Exception {
        log.info("开始转换任务");
        for (String dir : dirList) {
            try {
                log.info("当前目录:{}", dir);
                if (!dir.endsWith("/")) {
                    dir = dir + "/";
                }
                String finalDir = dir;
                Optional<Directory> opt = repository.findOne((r, q, b) -> b.equal(r.get(Directory_.PATH), finalDir));
                if (opt.isPresent()) {
                    log.info("目录{}已存在，跳过,详情:dirCode={}", dir, opt.orElse(null).getDirCode());
                    continue;
                }
                dir = FilenameUtils.normalize(dir, true);
                Directory directory = service.createDirectory(dir);
                completedDirList.add(directory);
                log.info("目录{}转换完成,dircode={}", directory.getPath(), directory.getDirCode());
            } catch (Exception e) {
                log.error("目录{}转换失败", dir, e);
                uncompletedDirList.add(dir);
            }
        }
    }

    @Override
    public void afterTaskFinish() throws Exception {
        Task.super.afterTaskFinish();
        log.info("转换成功{}个，失败{}个", completedDirList.size(), uncompletedDirList.size());
        IOUtils.writeLines(uncompletedDirList, "\r\n", new FileOutputStream("fail.txt"));
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
