package com.sheepfly.media.common.vo.file;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    /**
     * 上传组件使用的uid。
     */
    private String uid;
    /**
     * 下载链接。
     */
    private String url;
    /**
     * 扩展名。
     */
    private String extension;
    /**
     * 上传时间。
     */
    private Date uploadTime;
}
