package com.sheepfly.media.dataaccess.vo.file;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件信息。
 */
@Getter
@Setter
public class FileInfo {
    /**
     * 主键标识。
     */
    private String id;
    /**
     * 业务代码，用来关联业务。
     */
    private String businessCode;
    /**
     * 业务类型。
     */
    private Integer businessType;
    /**
     * 原始文件名。
     */
    private String originalFilename;
    /**
     * 实际文件名。
     */
    private String filename;
}
