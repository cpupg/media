package com.sheepfly.media.task.impl;

import cn.hutool.core.io.FileUtil;
import com.sheepfly.media.config.LoadDirectoryConfig;
import com.sheepfly.media.config.TaskConfig;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 扫描目录任务。
 */
@Slf4j
@Component
public class LoadDirectoryTaskImpl implements Task {
    private LoadDirectoryConfig config;

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

    /**
     * 加载指定目录下的资源，返回当前目录的子目录。
     *
     * @param dir 全路径。
     *
     * @return 子目录。
     */
    private String[] scanAndLoadDirectory(String dir) {
        File currentDir = new File(dir);
        String[] fileNameList = currentDir.list();
        for (String name : fileNameList) {
            String path = FileUtil.normalize(dir + File.separator + name);
            if (FileUtil.isDirectory(path)) {
                List<String> subFileNameList = FileUtil.listFileNames(path);
                if (subFileNameList.isEmpty()) {
                    log.warn("目录{}是空目录", path);
                } else {
                    scanAndLoadDirectory(path);
                }
            } else {
                Resource resource = new Resource();
                resource.setDir(dir);
                resource.setFilename(FileUtil.getName(path));
                resource.setCreateTime(new Date());
                resource.setDeleteStatus(0);
                log.info(resource.toString());
            }
        }
        return fileNameList;
    }
}
