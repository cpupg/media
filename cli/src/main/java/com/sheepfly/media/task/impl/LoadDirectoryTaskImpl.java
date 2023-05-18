package com.sheepfly.media.task.impl;

import com.sheepfly.media.config.LoadDirectoryConfig;
import com.sheepfly.media.config.TaskConfig;
import com.sheepfly.media.task.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * 扫描目录任务。
 */
@Slf4j
public class LoadDirectoryTaskImpl implements Task {
    private LoadDirectoryConfig config;

    @Override
    public void setTaskConfig(TaskConfig taskConfig) {
        this.config = (LoadDirectoryConfig) taskConfig;
    }

    @Override
    public void initializeTaskConfig() {

    }

    @Override
    public void executeTask() {

    }

    @Override
    public void getExecuteResult() {

    }
}
