package com.sheepfly.media.cli.task.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.cli.exception.IllegalCliStateException;
import com.sheepfly.media.cli.task.Task;
import com.sheepfly.media.cli.util.DirectoryCache;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.CommonException;
import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Resource;
import com.sheepfly.media.dataaccess.entity.Resource_;
import com.sheepfly.media.dataaccess.repository.AuthorRepository;
import com.sheepfly.media.dataaccess.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

@Slf4j
@Component
public class LoadSingleFileTaskImpl implements Task {
    private String usage;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private DirectoryCache cache;

    public LoadSingleFileTaskImpl() {
        InputStream inputStream = ResourceUtil.getStream("config/commandline.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            usage = properties.getProperty("loadSingleFile");
        } catch (IOException e) {
            log.error("初始化异常", e);
        }
    }

    @Override
    public void executeTask() {
        log.info("请输入作者用户名和资源全路径，用空格分隔。若输入了id，则会先使用id进行匹配");
        log.info("输入bye可以退出程序");
        Scanner scanner = new Scanner(System.in);
        log.info(usage);
        for (String line = scanner.nextLine(); !"bye".equals(line); line = scanner.nextLine()) {
            try {
                // win下shift+右键复制的路径带双引号，读取到的路径也包含双引号，会导致路径异常。
                // 比如："/a/b/c/d"读取带代码里是""/a/b/c/d""，最后得到的路径是"/a/b/c/d"/a/b/c/d"
                // 1.移除路径中的引号
                String lineNew = line.replace("\"", "");
                // 2.分割
                String[] arr = lineNew.split("\\|");
                // 3.裁剪空白
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].trim();
                }
                Resource resource = parseInputToResource(arr);
                log.info("保存成功:{} -> dirCode({})", resource.getFilename(), resource.getDirCode());
            } catch (IllegalCliStateException e) {
                // 此异常不需要堆栈
                log.error("任务异常:{}", e.getMessage());
            } catch (Throwable t) {
                log.error("发生异常", t);
            }
            log.info(usage);
        }
    }

    /**
     * 将输入内容解析为资源。
     *
     * <p>每一次输入只有一个作者和一个资源全路径，两者用空格分开。如果作者用户名或者资源全路径中有
     * 空格，需要用双引号包裹。如果用户名有重复，则会输出资源。</p>
     *
     * @param input 命令行输入内容。
     *
     * @return 输入内容对应的资源。
     *
     * @throws CommonException 异常。
     * @throws IllegalCliStateException 异常。
     */
    private Resource parseInputToResource(String[] input) throws IllegalCliStateException, CommonException {
        Resource resource = new Resource();
        Author author;
        String username;
        String path;
        String id = null;
        if (input.length == 2) {
            username = input[0];
            path = input[1];
        } else if (input.length == 3) {
            username = input[0];
            path = input[1];
            id = input[2];
        } else {
            throw new IllegalCliStateException("输入格式错误，请重试");
        }
        author = findAuthor(username, id);
        resource.setAuthorId(author.getId());
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalCliStateException("路径不存在:" + file.getAbsolutePath());
        }
        String parent = file.getAbsolutePath();
        if (file.isFile()) {
            parent = FileUtil.getParent(file.getAbsolutePath(), 1);
        }
        parent = FilenameUtils.normalize(parent, true);
        if (!parent.endsWith(Constant.SEPERATOR)) {
            parent = parent + Constant.SEPERATOR;
        }
        Directory d;
        try {
            d = cache.getOrCreateDirectory(parent);
        } catch (CommonException e) {
            throw new CommonException("获取目录失败", e);
        }
        resource.setDirCode(d.getDirCode());
        long count = resourceRepository.count((r, q, b) -> {
            Predicate p1 = b.equal(r.get(Resource_.DIR_CODE), d.getDirCode());
            Predicate p2 = b.equal(r.get(Resource_.FILENAME), file.getName());
            return b.and(p1, p2);
        });
        if (count > 0) {
            throw new CommonException("重复资源无法录入");
        }
        resource.setSaveTime(new Date());
        resource.setCreateTime(resource.getSaveTime());
        resource.setDeleteStatus(Constant.NOT_DELETED);
        resource.setFilename(file.getName());
        resource.setId(snowflake.nextIdStr());
        resourceRepository.save(resource);
        return resource;
    }

    private Author findAuthor(String username, String id) throws IllegalCliStateException {
        Author author;
        if (id != null) {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                log.info("作者存在");
                author = authorOptional.orElse(null);
                return author;
            }
        }
        if (authorRepository.countByUsername(username) == 0) {
            throw new IllegalCliStateException("作者不存在，请检查用户名！");
        } else if (authorRepository.countByUsername(username) > 1) {
            throw new IllegalCliStateException("有重名作者，请使用唯一标识重试");
        } else {
            author = authorRepository.findByUsername(username);
            return author;
        }
    }
}
