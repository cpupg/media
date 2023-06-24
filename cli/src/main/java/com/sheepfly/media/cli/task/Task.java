package com.sheepfly.media.cli.task;

import com.sheepfly.media.cli.config.TaskConfig;

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
     * <p>可以跳过配置直接执行任务。</p>
     *
     * @param taskConfig 任务配置。
     */
    default void setTaskConfig(TaskConfig taskConfig) {
    }

    /**
     * 初始化运行配置。可以是空方法，也可以做初始化操作。
     *
     * @throws Exception e
     */
    default void initializeTaskConfig() throws Exception {
    }

    /**
     * 执行任务。
     *
     * <p>所有字类都必须实现此方法。调用此方法说明任务已经配置完成，可以直接执行任务。</p>
     *
     * @throws Exception e
     */
    void executeTask() throws Exception;

    /**
     * 获取执行结果。
     *
     * <p>可以将执行结果以某种形式输出到文件，也可以输出到命令行，也可以什么都不做。</p>
     *
     * @throws Exception e
     */
    default void getExecuteResult() throws Exception {
    }

    /**
     * 返回任务准备状态，放返回true时可以执行任务。
     *
     * <p>可以用来判断任务配置是否完成。</p>
     *
     * @return 是否可以执行任务。
     *
     * @throws Exception e
     */
    default boolean ready() {
        return true;
    }

    /**
     * 任务执行完成后调用，可以用来做做一些扫尾工作，也可以什么都不做。
     *
     * @throws Exception e
     */
    default void afterTaskFinish() throws Exception {
    }
}
