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
     * 需要排除的目录对应的正则表达式。
     *
     * <p>可以是相对于程序运行目录的相对路径，也可以是绝对路径。</p>
     */
    private String[] excludeDirPattern;
    /**
     * 需要排除的文件对应的正则表达式。
     *
     * <p>可以是相对于运行目录的相对路径，也可以是绝对路径。</p>
     */
    private String[] excludeFilePattern;
    /**
     * 运行结果保存目录。默认是运行目录。
     */
    private String resultDir;
    /**
     * 作者名称。
     *
     * <p>若传入此参数，则使用此作者名称，网站为默认网站。若不传入此参数，使用默认作者。</p>
     */
    private String authorName;
    /**
     * 作者对应的唯一标识。
     */
    private String authorId;
}
