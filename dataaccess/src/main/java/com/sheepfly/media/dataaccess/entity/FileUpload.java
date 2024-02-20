package com.sheepfly.media.dataaccess.entity;

import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 */
@Data
@Entity
@Table(name = "MEDIA.FILE_UPLOAD")
public class FileUpload implements Serializable, LogicDelete {

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识
     */
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    /**
     * 原始文件名
     */
    @Column(name = "ORIGINAL_FILENAME", nullable = false)
    private String originalFilename;

    /**
     * 保存时的文件名
     */
    @Column(name = "FILENAME", nullable = false)
    private String filename;

    /**
     * 业务代码，用来关联业务
     */
    @Column(name = "BUSINESS_CODE", nullable = false)
    private String businessCode;

    /**
     * 业务类型，用来区分业务
     */
    @Column(name = "BUSINESS_TYPE", nullable = false)
    private Integer businessType;

    /**
     * 扩展名。
     */
    @Column(name = "EXTENSION", nullable = false)
    private String extension;

    /**
     * 上传状态1开始上传2上传完成。
     */
    @Column(name = "UPLOAD_STATUS", nullable = false)
    private Integer uploadStatus;

    /**
     * 删除状态
     */
    @Column(name = "DELETE_STATUS", nullable = false)
    private Integer deleteStatus;

    /**
     * 上传时间
     */
    @Column(name = "UPLOAD_TIME", nullable = false)
    private Date uploadTime;

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

}
