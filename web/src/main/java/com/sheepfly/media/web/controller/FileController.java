package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;
import com.sheepfly.media.service.base.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    private FileService service;

    @PostMapping("/upload")
    public ResponseData<Map> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file)
            throws IOException, BusinessException {
        String businessCode = request.getParameter("businessCode");
        // businessType是数字，入库前按字符串处理，入库时按数字处理
        String businessType = request.getParameter("businessType");
        if (StringUtils.isEmpty(businessCode) || StringUtils.isEmpty(businessType)) {
            throw new BusinessException(ErrorCode.FILE_EMPTY_BUSINESS_CODE_TYPE);
        }

        FileInfo fileInfo = service.uploadFile(file, businessCode, businessType);
        log.info("上传完成");
        return ResponseData.success(fileInfo);
    }

    @PostMapping("/queryFileList")
    public TableResponse<List<FileInfo>> queryFileList(@RequestParam("businessCode") String businessCode)
            throws BusinessException {
        log.info("获取文件列表，业务代码{}", businessCode);
        if (StringUtils.isEmpty(businessCode)) {
            throw new BusinessException(ErrorCode.FILE_EMPTY_BUSINESS_CODE);
        }
        return service.queryFileList(businessCode);
    }

    @GetMapping("/getFile")
    public void getFile(@RequestParam("id") String id, HttpServletResponse response) throws IOException {
        File file = service.getFile(id);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] bytes = IOUtils.readFully(is, is.available());
            os.write(bytes);
        }
    }
}
