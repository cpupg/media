package com.sheepfly.media.config;

import lombok.Data;

/**
 * 扫描指定目录的配置。
 *
 * @author wrote-code
 */
@Data
public class LoadDirectoryConfig implements TaskConfig {
    /**
     * 要扫描的目录。
     */
    private String targetDir;
    /**
     * 需要排除的目录。
     *
     * <p>可以是相对于程序运行目录的相对路径，也可以是绝对路径。</p>
     */
    private String[] excludeDir;
    /**
     * 需要排除的文件。
     *
     * <p>可以是相对于运行目录的相对路径，也可以是绝对路径。</p>
     */
    private String[] excludeFile;
    /**
     * 运行完后是否展示运行结果。默认true。
     */
    private boolean showResult = true;
    /**
     * 运行结果保存目录。默认是运行目录。
     */
    private String resultDir;
    /**
     * 运行结果文件名。
     *
     * <p>默认result，文件类型根据实际情况指定。注意：命令行参数会忽略文件类型。</p>
     */
    private String resultFile;
}
