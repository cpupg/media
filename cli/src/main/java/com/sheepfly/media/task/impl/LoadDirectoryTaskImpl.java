package com.sheepfly.media.task.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.config.LoadDirectoryConfig;
import com.sheepfly.media.config.TaskConfig;
import com.sheepfly.media.constant.Constant;
import com.sheepfly.media.entity.Author;
import com.sheepfly.media.entity.Author_;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.entity.Site;
import com.sheepfly.media.entity.Site_;
import com.sheepfly.media.form.data.ResourceData;
import com.sheepfly.media.repository.AuthorRepository;
import com.sheepfly.media.repository.ResourceRepository;
import com.sheepfly.media.repository.SiteRepository;
import com.sheepfly.media.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Autowired
    private Validator validator;

    private LoadDirectoryConfig config;
    /**
     * 命令行输入的作者。
     */
    private Author author;
    /**
     * 运行结果输出流。
     */
    private FileOutputStream resultOutputStream;
    /**
     * 录入失败的文件会存到这里。
     */
    private FileOutputStream failOutputStream;
    /**
     * 已录入文件。
     */
    private FileOutputStream duplicatedOutputStream;
    /**
     * 用于去重。
     */
    private ExampleMatcher matcher;
    /**
     * 扫描到的资源数据，用来进行校验。
     */
    private ResourceData resourceData = new ResourceData();

    @Override
    public void setTaskConfig(TaskConfig taskConfig) {
        this.config = (LoadDirectoryConfig) taskConfig;
    }

    @Override
    public void initializeTaskConfig() throws FileNotFoundException {
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

        // 查到就说明是相同文件。
        matcher = ExampleMatcher.matchingAll().withMatcher("dir", ele -> ele.exact())
                .withMatcher("filename", ele -> ele.exact());

        String resultPath = targetDir + File.separator + "result.txt";
        log.info("运行结果将保存到文件{}中", resultPath);
        // 初始化输出流，需要在afterTaskFinish中关闭。
        resultOutputStream = new FileOutputStream(resultPath, true);
        failOutputStream = new FileOutputStream(resultPath.replace(".txt", ".fail.txt"), true);
        duplicatedOutputStream = new FileOutputStream(resultPath.replace(".txt", ".dup.txt"), true);
        String time = String.format("--------->>>开始时间:%s<<<<<<----------",
                DateFormatUtils.format(System.currentTimeMillis(), Constant.STANDARD_TIME));
        writeMessage(time);
        writeFailMessage(time);
        writeDuplicatedMessage(time);
        writeMessage(String.format("当前用户:%s", author.getUsername()));
        writeMessage(String.format("扫描目录:%s", targetDir));
    }

    @Override
    public void executeTask() {
        log.info("当前目录:{},作者:{}", config.getTargetDir(), config.getAuthorName());
        String path = FileUtil.getAbsolutePath(config.getTargetDir());
        scanAndLoadDirectory(path);
    }

    @Override
    public void getExecuteResult() {
        // 暂时不需要
    }

    @Override
    public boolean ready() {
        // 其它参数已经通过setter设置，只有作者不确定，需要运行时判断。
        return author != null;
    }

    @Override
    public void afterTaskFinish() throws IOException {
        if (resultOutputStream != null) {
            resultOutputStream.close();
        }
        if (failOutputStream != null) {
            failOutputStream.close();
        }
        if (duplicatedOutputStream != null) {
            duplicatedOutputStream.close();
        }
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
            long count = resourceRepository.count(Example.of(resource, matcher));
            String message = String.format("%s -> %s", resource.getFilename(), resource.getDir());
            if (count > 0) {
                log.info("已经存在资源{} -> {}", resource.getFilename(), resource.getDir());
                writeDuplicatedMessage(message);
                return;
            }
            resource.setCreateTime(new Date());
            resource.setDeleteStatus(0);
            resource.setAuthorId(author.getId());
            resource.setId(snowflake.nextIdStr());
            try {
                BeanUtils.copyProperties(resource, resourceData);
                Set<ConstraintViolation<ResourceData>> result = validator.validate(resourceData);
                if (!result.isEmpty()) {
                    log.warn("资源保存失败:{} -> {}", resource.getFilename(), resource.getDir());
                    writeFailMessage(message);
                    result.forEach(ele -> writeFailMessage(
                            String.format("    | -> %s -> %s", ele.getMessage(), ele.getInvalidValue())));
                    return;
                }
                resourceRepository.save(resource);
                writeMessage(message);
            } catch (Exception e) {
                // 不能影响后面资源的保存。
                log.error("资源保存失败:{} -> {}", resource.getFilename(), resource.getDir(), e);
                writeFailMessage(message);
            }
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

    private void writeMessage(String message) {
        try {
            IOUtils.write(message, resultOutputStream, StandardCharsets.UTF_8);
            IOUtils.write("\r\n", resultOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("写入运行结果失败", e);
        }
    }

    private void writeFailMessage(String message) {
        try {
            IOUtils.write(message, failOutputStream, StandardCharsets.UTF_8);
            IOUtils.write("\r\n", failOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("写入失败结果失败", e);
        }
    }

    private void writeDuplicatedMessage(String message) {
        try {
            IOUtils.write(message, duplicatedOutputStream, StandardCharsets.UTF_8);
            IOUtils.write("\r\n", duplicatedOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("写入重复结果失败", e);
        }
    }
}
