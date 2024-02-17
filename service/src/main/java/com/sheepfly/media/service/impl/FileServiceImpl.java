package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.dataaccess.entity.FileUpload;
import com.sheepfly.media.dataaccess.repository.FileUploadRepository;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;
import com.sheepfly.media.service.base.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileUploadRepository repository;
    @Autowired
    private Snowflake snowflake;

    @Override
    public FileInfo uploadFile(FileUpload fileUploadInfo) {
        FileInfo fileInfo = new FileInfo();
        if (!StringUtils.isEmpty(fileUploadInfo.getId())) {
            // 文件已上传，这次调用是用来更新状态。
            log.info("文件已上传，修改上传状态。");
            FileUpload f = new FileUpload();
            f.setId(fileUploadInfo.getId());
            f.setUploadStatus(AFTER_UPLOAD);
            FileUpload save = repository.save(f);
            log.info("修改完成{}", save);
            BeanUtils.copyProperties(fileUploadInfo, fileInfo);
            return fileInfo;
        }
        fileUploadInfo.setId(snowflake.nextIdStr());
        fileUploadInfo.setDeleteStatus(Constant.NOT_DELETED);
        fileUploadInfo.setUploadStatus(START_UPLOAD);
        fileUploadInfo.setUploadTime(new Date());
        log.info("上传文件，文件信息:{}", fileUploadInfo);
        FileUpload save = repository.save(fileUploadInfo);
        log.info("上传完成{}", save);
        BeanUtils.copyProperties(save, fileInfo);
        return fileInfo;
    }
}
