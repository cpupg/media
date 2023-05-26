package com.sheepfly.media.task.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.config.LoadDirectoryConfig;
import com.sheepfly.media.config.TaskConfig;
import com.sheepfly.media.entity.Author;
import com.sheepfly.media.entity.Author_;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.entity.Site_;
import com.sheepfly.media.repository.AuthorRepository;
import com.sheepfly.media.repository.ResourceRepository;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 扫描目录任务。
 */
@Slf4j
@Component
public class LoadDirectoryTaskImpl implements Task {
    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private Snowflake snowflake;

    private LoadDirectoryConfig config;
    /**
     * 命令行输入的作者。
     */
    private Author author;

    @Override
    public void setTaskConfig(TaskConfig taskConfig) {
        this.config = (LoadDirectoryConfig) taskConfig;
    }

    @Override
    public void initializeTaskConfig() {
        String targetDir = config.getTargetDir();
        if (!FileUtil.isAbsolutePath(targetDir)) {
            log.warn("目标路径不是绝对路径");
            String absolutePath = new File(targetDir).getAbsolutePath();
            log.info("绝对路径:{}", absolutePath);
            config.setTargetDir(absolutePath);
        }
        if (StringUtils.isNotBlank(config.getAuthorId())) {
            Optional<Author> optionalAuthor = authorRepository.findOne(
                    (root, query, builder) -> builder.equal(root.get(Author_.id), config.getAuthorId()));
            author = optionalAuthor.orElse(null);
            return;
        }
        if (StringUtils.isNotBlank(config.getAuthorName())) {
            List<Author> authorList = authorRepository.findAll(
                    (root, query, builder) -> builder.equal(root.get(Author_.USERNAME), config.getAuthorName()));
            if (authorList.isEmpty()) {
                log.warn("没有匹配的作者，请重试");
                return;
            } else if (authorList.size() == 1) {
                log.info("匹配到唯一作者");
                author = authorList.get(0);
            } else if (authorList.size() <= 5) {
                log.warn("有重名作者，请输入id后重试" + authorList);
                return;
            } else {
                log.warn("重名作者太多，请确定作者名称后重试");
                return;
            }
        }
        Optional<Site> optionalSite = siteRepository.findOne(
                (root, query, builder) -> builder.equal(root.get(Site_.id), author.getSiteId()));
        log.info("当前作者用户名{},来源{}", author.getUsername(), optionalSite.orElse(null));
    }

    @Override
    public void executeTask() {
        log.info("当前目录:{},作者:{}", config.getTargetDir(), config.getAuthorName());
        String path = FileUtil.getAbsolutePath(config.getTargetDir());
        scanAndLoadDirectory(path);
    }

    @Override
    public void getExecuteResult() {
        // todo
    }

    @Override
    public boolean ready() {
        // 其它参数已经通过setter设置，只有作者不确定，需要运行时判断。
        return author != null;
    }

    /**
     * 加载指定目录下的资源，返回当前目录的子目录。
     *
     * @param dir 全路径。
     *
     * @return 子目录。
     */
    private void scanAndLoadDirectory(String dir) {
        File currentPath = new File(dir);
        if (FileUtil.isFile(dir)) {
            Resource resource = new Resource();
            resource.setDir(FileUtil.getParent(dir, 1));
            resource.setFilename(FileUtil.getName(dir));
            resource.setCreateTime(new Date());
            resource.setDeleteStatus(0);
            resource.setAuthorId(author.getId());
            resource.setId(snowflake.nextIdStr());
            resourceRepository.save(resource);
            log.info("资源保存成功,文件名:{},作者:{},目录{}", resource.getFilename(), author.getUsername(),
                    resource.getDir());
            return;
        }
        String[] fileNameList = currentPath.list();
        if (fileNameList.length == 0) {
            log.info("目录{}是空目录", currentPath);
            return;
        }
        for (String name : fileNameList) {
            scanAndLoadDirectory(currentPath + File.separator + name);
        }

    }
}
