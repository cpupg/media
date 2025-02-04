package com.sheepfly.media.common.vo.file;

import java.util.Date;

/**
 * 文件信息。
 */
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessCode() {
        return this.businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public Integer getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getOriginalFilename() {
        return this.originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
