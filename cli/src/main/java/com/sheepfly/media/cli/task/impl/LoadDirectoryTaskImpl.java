package com.sheepfly.media.cli.task.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.cli.config.LoadDirectoryConfig;
import com.sheepfly.media.cli.config.TaskConfig;
import com.sheepfly.media.cli.task.Task;
import com.sheepfly.media.cli.util.DirectoryCache;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.CommonException;
import com.sheepfly.media.common.form.data.ResourceData;
import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.dataaccess.entity.Author_;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.Resource_;
import com.sheepfly.media.dataaccess.entity.Site;
import com.sheepfly.media.dataaccess.entity.Site_;
import com.sheepfly.media.dataaccess.repository.AuthorRepository;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import com.sheepfly.media.dataaccess.repository.SiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
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
    private ResourceData resourceData = new ResourceData();
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
    @Autowired
    private DirectoryCache cache;
    private LoadDirectoryConfig config;
    /**
     * 录入成功的资源数量。
     */
    private int successCount = 0;
    /**
     * 录入失败的资源数量。
     */
    private int failCount = 0;
    /**
     * 排除的资源数量。
     */
    private int excludeCount = 0;
    /**
     * 包含的资源数量。
     */
    private int includeCount = 0;
    /**
     * 重复资源数量。
     */
    private int duplicatedCount = 0;
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

        Optional<Site> optionalSite = siteRepository.findOne(
                (root, query, builder) -> builder.equal(root.get(Site_.id), author.getSiteId()));
        log.info("当前作者用户名{},来源{}", author.getUsername(), optionalSite.orElse(null));
        // 查到就说明是相同文件。
        matcher = ExampleMatcher.matchingAll().withMatcher(Resource_.DIR_CODE, ExampleMatcher.GenericPropertyMatcher::exact)
                .withMatcher(Resource_.FILENAME, ExampleMatcher.GenericPropertyMatcher::exact)
                .withMatcher(Resource_.DELETE_STATUS, ExampleMatcher.GenericPropertyMatcher::exact);

        writeMessage(String.format("当前用户:%s", author.getUsername()));
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
                    (root, query, builder) -> {
                        Predicate p1 = builder.equal(root.get(Author_.USERNAME), config.getAuthorName());
                        Predicate p2 = builder.equal(root.get(Author_.DELETE_STATUS), Constant.NOT_DELETED);
                        return builder.and(p1, p2);
                    });
            if (authorList.isEmpty()) {
                log.warn("没有匹配的作者，请重试");
            } else if (authorList.size() == 1) {
                log.info("匹配到唯一作者");
                author = authorList.get(0);
            } else if (authorList.size() <= 5) {
                log.warn("有重名作者，请输入id后重试" + authorList);
            } else {
                log.warn("重名作者太多，请确定作者名称后重试");
            }
        } else {
            // 没有作者
            log.error("author-id和author至少需要输入一个");
        }
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
            // 结果文件不需要扫描且不需要记录
            if (name.matches("^result.*\\.txt$")) {
                log.info("忽略结果文件{}", name);
                return false;
            }
            // 先校验排除路径，再校验包含路径
            String path = dir.getAbsolutePath();
            for (String e : config.getExcludePathArray()) {
                if (path.matches(e) || name.matches(e)) {
                    excludeCount++;
                    writeExcludeMessage(String.format("exclude: %s -> %s%s%s", e, path, File.separator, name));
                    return false;
                }
            }
            // 当包含条件不为空时，只有符合条件的才会列出，不符合条件的会忽略。
            for (String e : config.getIncludePathArray()) {
                if (path.matches(e) || name.matches(e)) {
                    writeMessage(String.format("%s -> %s%s%s", e, path, File.separator, name));
                    includeCount++;
                    writeIncludeMessage(String.format("include: %s -> %s%s%s", e, path, File.separator, name));
                    return true;
                } else {
                    excludeCount++;
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
        StringBuilder builder = new StringBuilder();
        builder.append("任务执行完成，执行结果如下：")
                .append(System.lineSeparator())
                .append("录入成功的资源数量：").append(successCount).append(System.lineSeparator())
                .append("录入失败的资源数量：").append(failCount).append(System.lineSeparator())
                .append("排除的资源数量：").append(excludeCount).append(System.lineSeparator())
                .append("包含的资源数量：").append(includeCount).append(System.lineSeparator())
                .append("重复资源数量：").append(duplicatedCount).append(System.lineSeparator());
        log.info(builder.toString());
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
     */
    private void scanAndLoadDirectory(String dir) {
        log.info("当前路径:{}", dir);
        File currentPath = new File(dir);
        if (FileUtil.isFile(dir)) {
            Resource resource = new Resource();
            String parent = FileUtil.getParent(dir, 1);
            parent = FilenameUtils.normalize(parent, true);
            if (!parent.endsWith(Constant.SEPERATOR)) {
                parent = parent + Constant.SEPERATOR;
            }
            Directory d = null;
            try {
                d = cache.getOrCreateDirectory(parent);
            } catch (CommonException e) {
                log.error("获取目录失败:{}", d.getPath(), e);
                failCount++;
                writeFailMessage(String.format("获取目录失败:%s,exception=%s,cause=%s", dir, e.getMessage(),
                        e.getCause().getMessage()));
                // 目录获取失败意味着dirCode无法获取，无法满足数据库校验
                return;
            }
            resource.setFilename(FileUtil.getName(dir));
            resource.setDeleteStatus(Constant.NOT_DELETED);
            resource.setDirCode(d.getDirCode());
            long count = resourceRepository.count(Example.of(resource, matcher));
            String message = String.format("%s -> %s", resource.getFilename(), parent);
            if (count > 0) {
                log.info("已经存在资源{} -> {}", resource.getFilename(), parent);
                duplicatedCount++;
                writeDuplicatedMessage(message);
                return;
            }
            resource.setSaveTime(new Date());
            resource.setCreateTime(resource.getSaveTime());
            resource.setDeleteStatus(0);
            resource.setAuthorId(author.getId());
            resource.setId(snowflake.nextIdStr());
            try {
                BeanUtils.copyProperties(resource, resourceData);
                Set<ConstraintViolation<ResourceData>> result = validator.validate(resourceData);
                if (!result.isEmpty()) {
                    log.warn("资源保存失败:{} -> {}", resource.getFilename(), parent);
                    failCount++;
                    writeFailMessage(message);
                    result.forEach(ele -> writeFailMessage(
                            String.format("    | -> %s -> %s", ele.getMessage(), ele.getInvalidValue())));
                    return;
                }
                resourceRepository.save(resource);
                successCount++;
                writeMessage(message);
            } catch (Exception e) {
                // 不能影响后面资源的保存。
                log.error("资源保存失败:{} -> {}", resource.getFilename(), parent, e);
                failCount++;
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
        log.info("当前路径是目录，有{}个子路径", fileNameList.length);
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
