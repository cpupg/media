package com.sheepfly.media.config.bean;

import lombok.Data;

/**
 * 应用版本。
 *
 * @author wrote-code
 */
@Data
public class Version {
    /**
     * 主版本号。
     *
     * <p>主版本号从配置文件中获取，配置文件由工作流生成，因此不会包含在代码仓库中。</p>
     */
    private String mainVersion;
}
