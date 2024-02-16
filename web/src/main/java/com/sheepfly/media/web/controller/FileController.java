package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.http.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload")
    public ResponseData<Map> upload(@RequestParam(value = "businessId", required = false) String businessId,
            @RequestParam("file") MultipartFile file) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        String originalFilename = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFilename);
        String filename = format.format(System.currentTimeMillis());
        log.info("上传文件{}", originalFilename);
        File f = new File(fileDir + "/" + filename + "." + ext);
        InputStream is = file.getInputStream();
        FileUtils.createParentDirectories(f);
        OutputStream os = new FileOutputStream(f);
        byte[] bytes = IOUtils.readFully(is, is.available());
        IOUtils.write(bytes, os);
        log.info("上传完成");
        return ResponseData.success();
    }
}
