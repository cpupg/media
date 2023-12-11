package com.sheepfly.media.cli.task.impl;

import com.sheepfly.media.cli.task.Task;
import com.sheepfly.media.common.exception.CommonException;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Directory_;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.service.base.DirectoryService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private ResourceRepository resourceRepository;
    private String dropTemp;
    private String createTemp;
    private String queryDirectory;
    private String queryResource;
    private String insertTemp;
    private String updateResource;
    private List<String> dirList;
    private List<Resource> completedResourceList;
    private List<Resource> uncompletedResourceList;
    private Map<String, Directory> directoryMap;

    public TransFormDirectoryTaskImpl() throws IOException {
        log.info("加载sql");
        ClassPathResource resource = new ClassPathResource("config/jdbc-template.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        dropTemp = properties.getProperty("dropTemp");
        createTemp = properties.getProperty("createTemp");
        queryDirectory = properties.getProperty("queryDirectory");
        queryResource = properties.getProperty("queryResource");
        insertTemp = properties.getProperty("insertTemp");
        updateResource = properties.getProperty("updateResource");
        log.info("sql加载完成");
        completedResourceList = new ArrayList<>();
        uncompletedResourceList = new ArrayList<>();
        directoryMap = new HashMap<>();
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
        transForm();
        log.info("转换完成，成功{}，失败{}", completedResourceList.size(), uncompletedResourceList.size());
    }

    private void transForm() throws CommonException {
        long total = resourceRepository.count();
        int pageSize = 20;
        int pages = (int) (total / pageSize);
        if (total % pageSize > 0) {
            pages++;
        }
        log.info("资源总数:{},每页容量{},总页数{}", total, pageSize, pages);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= pages; i++) {
            log.info("------>>>当前页{}", i);
            int offset = pageSize * (i - 1);
            map.put("limit", pageSize);
            map.put("offset", offset);
            List<Resource> list = npJdbcTemplate.query(queryResource, map,
                    BeanPropertyRowMapper.newInstance(Resource.class));
            for (Resource res : list) {
                log.info("处理资源{} -> {}", res.filename, res.dir);
                if (!res.dir.endsWith("/")) {
                    res.dir = res.dir + "/";
                }
                if (res.dir.endsWith(res.filename + "/")) {
                    res.dir = res.dir.substring(0, res.dir.length() - res.filename.length() - 1);
                }
                Directory directory = getDirectory(res.dir);
                DirCode dirCode = new DirCode();
                dirCode.id = res.id;
                dirCode.dirCode = directory.getDirCode();
                int update = npJdbcTemplate.update(insertTemp, new BeanPropertySqlParameterSource(dirCode));
                log.info("插入完成{},开始更新源表", update);
                res.dirCode = dirCode.dirCode;
                update = npJdbcTemplate.update(updateResource, new BeanPropertySqlParameterSource(res));
                log.info("更新完成{}", update);
                completedResourceList.add(res);
            }
            log.info("查询完成:{}", list.size());
        }
    }

    private Directory getDirectory(String dir) throws CommonException {
        log.info("当前目录:{}", dir);
        dir = FilenameUtils.normalize(dir, true);
        dir = dir.charAt(0) + dir.substring(1);
        if (directoryMap.containsKey(dir)) {
            log.info("缓存命中目录");
            return directoryMap.get(dir);
        }
        String finalDir = dir;
        Optional<Directory> one = repository.findOne((r, q, b) -> b.equal(r.get(Directory_.PATH), finalDir));
        if (one.isPresent()) {
            log.info("数据库中有目录，加入缓存{}", dir);
            directoryMap.put(dir, one.orElse(null));
            return one.orElse(null);
        }
        log.info("创建目录:{}", dir);
        Directory directory = service.createDirectory(dir);
        if (directory == null) {
            throw new CommonException("创建目录失败");
        }
        directoryMap.put(dir, directory);
        log.info("目录加入缓存成功");
        return directory;
    }

    @Override
    public void afterTaskFinish() throws Exception {
        Task.super.afterTaskFinish();
    }

    @Getter
    @Setter
    private static class DirCode {
        public String id;
        public Long dirCode;
    }

    @Getter
    @Setter
    private static class Resource {
        public String id;
        public String filename;
        public String dir;
        public long dirCode;
    }
}
