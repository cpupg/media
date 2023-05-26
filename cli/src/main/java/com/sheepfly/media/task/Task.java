package com.sheepfly.media.task;

import com.sheepfly.media.config.TaskConfig;

/**
 * 批量任务。
 *
 * <p>通过cli调用的批量任务。</p>
 *
 * @author wrote-code
 */
public interface Task {
    /**
     * 设置任务运行配置。
     *
     * @param taskConfig 任务配置。
     */
    void setTaskConfig(TaskConfig taskConfig);

    /**
     * 初始化运行配置。可以是空方法，也可以做初始化操作。
     */
    void initializeTaskConfig();

    /**
     * 开始执行任务。
     */
    void executeTask();

    /**
     * 获取执行结果。
     *
     * <p>可以将执行结果以某种形式输出到文件，也可以输出到命令行，也可以什么都不做。</p>
     */
    void getExecuteResult();

    /**
     * 返回任务准备状态，放返回true时可以执行任务。
     *
     * <p>可以用来判断任务配置是否完成。</p>
     *
     * @return 是否可以执行任务。
     */
    boolean ready();
}
