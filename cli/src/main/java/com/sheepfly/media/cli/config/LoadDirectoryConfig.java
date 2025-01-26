package com.sheepfly.media.cli.config;

/**
 * 扫描指定目录的配置。
 *
 * @author wrote-code
 */
public class LoadDirectoryConfig implements TaskConfig {
    /**
     * 要扫描的目录。
     */
    private String targetDir;
    /**
     * 需要排除的路径对应的正则表达式。
     */
    private String[] excludePathArray = {};
    /**
     * 需要包含的路径对应的正则表达式。
     */
    private String[] includePathArray = {};
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

    public String getTargetDir() {
        return this.targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String[] getExcludePathArray() {
        return this.excludePathArray;
    }

    public void setExcludePathArray(String[] excludePathArray) {
        this.excludePathArray = excludePathArray;
    }

    public String[] getIncludePathArray() {
        return this.includePathArray;
    }

    public void setIncludePathArray(String[] includePathArray) {
        this.includePathArray = includePathArray;
    }

    public String getResultDir() {
        return this.resultDir;
    }

    public void setResultDir(String resultDir) {
        this.resultDir = resultDir;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
