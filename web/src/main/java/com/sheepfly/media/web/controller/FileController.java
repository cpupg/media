package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.dataaccess.entity.FileUpload;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;
import com.sheepfly.media.service.base.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    /**
     * 文件保存目录。
     *
     * <p>从tempDir拷贝文件过来，可以选择是否删除。需要指定业务代码，一个业务代码一个子目录。
     * 如果没有业务代码，则传到根目录。</p>
     */
    @Value("${media.file.file-dir}")
    private String fileDir;
    @Autowired
    private FileService service;

    @PostMapping("/upload")
    public ResponseData<Map> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file)
            throws IOException, BusinessException {
        String businessCode = request.getParameter("businessCode");
        String businessType = request.getParameter("businessType");
        if (StringUtils.isEmpty(businessCode) || StringUtils.isEmpty(businessType)) {
            throw new BusinessException(ErrorCode.FILE_EMPTY_BUSINESS_CODE_TYPE);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        String originalFilename = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFilename);
        String filename = format.format(System.currentTimeMillis());
        log.info("上传文件{}", originalFilename);
        File f = new File(fileDir + "/" + filename + "." + ext);
        FileUtils.createParentDirectories(f);
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(f)) {
            log.info("复制文件到系统目录");
            byte[] bytes = IOUtils.readFully(is, is.available());
            IOUtils.write(bytes, os);
            log.info("复制完成");
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setBusinessCode(businessCode);
        fileUpload.setBusinessType(Integer.parseInt(businessType));
        fileUpload.setOriginalFilename(originalFilename);
        fileUpload.setFilename(f.getName());
        FileInfo fileInfo = service.uploadFile(fileUpload);
        log.info("上传完成");
        return ResponseData.success(fileInfo);
    }
}
