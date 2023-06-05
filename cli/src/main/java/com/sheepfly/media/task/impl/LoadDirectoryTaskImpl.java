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
import java.io.FilenameFilter;
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
    /**
     * 扫描到的资源数据，用来进行校验。
     */
    private final ResourceData resourceData = new ResourceData();
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
     * 排除掉的路径。
     */
    private FileOutputStream excludedOutputStream;
    /**
     * 要包含的路径。
     */
    private FileOutputStream includedOutputStream;
    /**
     * 用于去重。
     */
    private ExampleMatcher matcher;
    /**
     * 文件名过滤器。
     */
    private FilenameFilter filenameFilter;

    @Override
    public void setTaskConfig(TaskConfig taskConfig) {
        this.config = (LoadDirectoryConfig) taskConfig;
    }

    @Override
    public void initializeTaskConfig() throws FileNotFoundException {
        configDirAndFile();
        String time = String.format("--------->>>开始时间:%s<<<<<<----------",
                DateFormatUtils.format(System.currentTimeMillis(), Constant.STANDARD_TIME));
        writeMessage(time);
        writeFailMessage(time);
        writeDuplicatedMessage(time);
        writeExcludeMessage(time);
        writeIncludeMessage(time);
        writeMessage(String.format("扫描目录:%s", config.getTargetDir()));
        configAuthor();
        configIncludeAndExclude();
        log.info("配置完成");
        writeMessage("   ->配置完成<-");
    }

    /**
     * 配置作者。
     */
    private void configAuthor() {
        if (StringUtils.isNotBlank(config.getAuthorId())) {
            Optional<Author> optionalAuthor = authorRepository.findOne(
                    (root, query, builder) -> builder.equal(root.get(Author_.id), config.getAuthorId()));
            author = optionalAuthor.orElse(null);
        }
        if (author != null) {
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
        } else {
            // 没有作者
            log.error("author-id和author至少需要输入一个");
            return;
        }
        Optional<Site> optionalSite = siteRepository.findOne(
                (root, query, builder) -> builder.equal(root.get(Site_.id), author.getSiteId()));
        log.info("当前作者用户名{},来源{}", author.getUsername(), optionalSite.orElse(null));
        // 查到就说明是相同文件。
        matcher = ExampleMatcher.matchingAll().withMatcher("dir", ExampleMatcher.GenericPropertyMatcher::exact)
                .withMatcher("filename", ExampleMatcher.GenericPropertyMatcher::exact);

        writeMessage(String.format("当前用户:%s", author.getUsername()));
    }

    /**
     * 配置包含路径和排除路径。
     *
     * <p>若要扫描的目录里有很多文件/目录，不建议输入多个包含路径或排除路径，会影响性能。</p>
     */
    private void configIncludeAndExclude() {
        StringBuilder builder = new StringBuilder();
        builder.append("要包含的路径:").append(System.lineSeparator());
        for (String e : config.getExcludePathArray()) {
            builder.append("    ->").append(e).append(System.lineSeparator());
        }
        builder.append("要排除的路径:").append(System.lineSeparator());
        for (String e : config.getIncludePathArray()) {
            builder.append("    ->").append(e).append(System.lineSeparator());
        }
        writeMessage(builder.toString());
        // 1.排除优先级要比包含高。
        // 2.如果输入了包含路径，则只有匹配包含路径的资源才会被添加
        // 3.不建议同时输入包含和排除
        filenameFilter = (dir, name) -> {
            // 先校验排除路径，再校验包含路径
            String path = dir.getAbsolutePath();
            for (String e : config.getExcludePathArray()) {
                if (path.matches(e) || name.matches(e)) {
                    writeExcludeMessage(String.format("exclude: %s -> %s%s%s", e, path, File.separator, name));
                    return false;
                }
            }
            // 当包含条件不为空时，只有符合条件的才会列出，不符合条件的会忽略。
            for (String e : config.getIncludePathArray()) {
                if (path.matches(e) || name.matches(e)) {
                    writeMessage(String.format("%s -> %s%s%s", e, path, File.separator, name));
                    writeIncludeMessage(String.format("include: %s -> %s%s%s", e, path, File.separator, name));
                    return true;
                } else {
                    writeExcludeMessage(String.format("not include: %s -> %s%s%s", e, path, File.separator, name));
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * 配置目录和文件。
     *
     * <p>要扫描的目录，一级要包括和排除的文件和目录。</p>
     *
     * @throws FileNotFoundException 文件不存在。
     */
    private void configDirAndFile() throws FileNotFoundException {
        String targetDir = config.getTargetDir();
        if (!FileUtil.isAbsolutePath(targetDir)) {
            log.warn("目标路径不是绝对路径");
            String absolutePath = new File(targetDir).getAbsolutePath();
            log.info("绝对路径:{}", absolutePath);
            config.setTargetDir(absolutePath);
        }
        String resultPath = targetDir + File.separator + "result";
        log.info("运行结果将保存到文件{}中", resultPath);
        // 初始化输出流，需要在afterTaskFinish中关闭。
        resultOutputStream = new FileOutputStream(resultPath + ".txt", true);
        failOutputStream = new FileOutputStream(resultPath + ".fail.txt", true);
        duplicatedOutputStream = new FileOutputStream(resultPath + ".dup.txt", true);
        excludedOutputStream = new FileOutputStream(resultPath + ".ex.txt", true);
        includedOutputStream = new FileOutputStream(resultPath + ".inc.txt", true);
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
        String[] fileNameList = currentPath.list(filenameFilter);
        String[] fullFileNameList = currentPath.list();
        if (fileNameList.length < fullFileNameList.length) {
            log.warn("路径{}下有文件被排除", dir);
        }
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

    private void writeExcludeMessage(String message) {
        try {
            IOUtils.write(message, excludedOutputStream, StandardCharsets.UTF_8);
            IOUtils.write("\r\n", excludedOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("写入排除结果失败", e);
        }
    }

    private void writeIncludeMessage(String message) {
        try {
            IOUtils.write(message, includedOutputStream, StandardCharsets.UTF_8);
            IOUtils.write("\r\n", includedOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("写入包含结果失败", e);
        }
    }
}
