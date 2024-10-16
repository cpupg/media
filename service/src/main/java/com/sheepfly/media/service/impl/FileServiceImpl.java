package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.FileUpload;
import com.sheepfly.media.dataaccess.mapper.FileMapper;
import com.sheepfly.media.dataaccess.repository.FileUploadRepository;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;
import com.sheepfly.media.service.base.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    /**
     * String.format拼接文件时的格式化字符串。fileDir/getDir/filename
     */
    private static final String FILE_FORMAT_PATTERN = "%s/%s/%s";
    @Autowired
    private FileUploadRepository repository;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private FileMapper mapper;
    @Autowired
    private Properties businessType;
    /**
     * 文件保存目录。
     *
     * <p>从tempDir拷贝文件过来，可以选择是否删除。需要指定业务代码，一个业务代码一个子目录。
     * 如果没有业务代码，则传到根目录。</p>
     */
    @Value("${media.file.file-dir}")
    private String fileDir;
    /**
     * 临时上传目录。
     */
    @Value("${media.file.temp-dir}")
    private String tempDir;
    /**
     * 回收站。
     */
    @Value("${media.file.recycle-bin}")
    private String recycleBin;

    @Override
    public FileInfo uploadFile(MultipartFile file, String businessCode, String businessType) throws IOException {
        log.info("上传文件:{}", file.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmsssss");
        Date date = new Date();
        String originalFilename = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFilename);
        if (ext.length() > 0) {
            ext = "." + ext;
        }
        // 保存时使用时间戳保存文件
        String filename = format.format(System.currentTimeMillis());
        File f = new File(String.format("%s/%s/%s%s", fileDir, getFileDir(businessType, date), filename, ext));
        // 目前没有预览功能，将所有文件放在一起方便预览。
        File f2 = new File(String.format("%s/%s%s", tempDir, filename, ext));
        FileUtils.createParentDirectories(f);
        FileUtils.createParentDirectories(f2);
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(f);
             OutputStream os2 = new FileOutputStream(f2)) {
            log.info("复制文件到上传目录");
            byte[] bytes = IOUtils.readFully(is, is.available());
            IOUtils.write(bytes, os);
            IOUtils.write(bytes, os2);
            log.info("复制完成");
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setBusinessCode(businessCode);
        fileUpload.setBusinessType(Integer.parseInt(businessType));
        fileUpload.setOriginalFilename(originalFilename);
        fileUpload.setFilename(f.getName());
        fileUpload.setExtension(FilenameUtils.getExtension(originalFilename));

        FileInfo fileInfo = new FileInfo();
        // if (!StringUtils.isEmpty(fileUpload.getId())) {
        // todo 文件上传二次确认
        // 上传过程可能意外终止，若此时文件已经保存到指定目录，则有于上传终止，此文件成为脏文件。
        // 只有经过二次确认的文件才能视为上传成功。
        //     // 文件已上传，这次调用是用来更新状态。
        //     log.info("文件已上传，修改上传状态。");
        //     FileUpload f = new FileUpload();
        //     f.setId(fileUpload.getId());
        //     f.setUploadStatus(AFTER_UPLOAD);
        //     FileUpload save = repository.save(f);
        //     log.info("修改完成{}", save);
        //     BeanUtils.copyProperties(fileUpload, fileInfo);
        //     return fileInfo;
        // }
        fileUpload.setId(snowflake.nextIdStr());
        fileUpload.setDeleteStatus(Constant.NOT_DELETED);
        fileUpload.setUploadStatus(START_UPLOAD);
        fileUpload.setUploadTime(new Date());
        log.info("上传文件，文件信息:{}", fileUpload);
        FileUpload save = repository.save(fileUpload);
        log.info("上传完成{}", save);
        BeanUtils.copyProperties(save, fileInfo);
        return fileInfo;
    }

    @Override
    public TableResponse<FileInfo> queryFileList(String businessCode) {
        // todo 分页
        List<FileInfo> list = mapper.queryFileList(businessCode);
        return TableResponse.success(list, (long) list.size());
    }


    @Override
    public String getFileDir(String businessType, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s/%s", businessType, year, month, day);
    }

    @Override
    public FileUpload deleteFile(String id) throws BusinessException {
        Optional<FileUpload> opt = repository.findById(id);
        if (!opt.isPresent() || opt.orElse(null).getDeleteStatus() == Constant.DELETED) {
            throw new BusinessException(ErrorCode.FILE_NOT_FOUND_ERROR);
        }
        FileUpload fileUpload = opt.orElse(null);
        File file = getFile(id);
        if (file == null || !file.exists()) {
            throw new BusinessException(ErrorCode.FILE_NOT_FOUND);
        }
        log.info("删除文件:{}", file);
        String dir = getFileDir(String.valueOf(fileUpload.getBusinessType()), fileUpload.getUploadTime());
        File file2 = new File(String.format(FILE_FORMAT_PATTERN, recycleBin, dir, fileUpload.getFilename()));
        try {
            FileUtils.moveFile(file, file2);
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.UNEXPECT_ERROR, e);
        }
        fileUpload.setId(id);
        fileUpload.setDeleteTime(new Date());
        fileUpload.setDeleteStatus(Constant.DELETED);
        return repository.save(fileUpload);
    }

    @Override
    public String getBusinessType(String key) {
        return businessType.getProperty(key);
    }

    @Override
    public int deleteFileByBusinessCode(String businessCode) throws BusinessException {
        List<FileInfo> list = mapper.queryFileList(businessCode);
        log.info("业务代码{}下有{}个文件", businessCode, list.size());
        // 先判断文件是否存在，然后再删除
        // 若中途删除失败，则已删除的文件只能手动删除
        List<File> fileList = new ArrayList<>();
        for (FileInfo fileInfo : list) {
            String dir = getFileDir(String.valueOf(fileInfo.getBusinessType()), fileInfo.getUploadTime());
            File file = getFile(dir, fileInfo.getFilename());
            if (file.exists()) {
                fileList.add(file);
            } else {
                log.warn("文件不存在:{}", fileInfo);
                throw new BusinessException(ErrorCode.FILE_NOT_FOUND);
            }
        }
        int count = 0;
        for (File file : fileList) {
            if (file.delete()) {
                count++;
                log.info("文件{}删除完成", file);
            } else {
                log.warn("文件{}删除失败", file);
                throw new BusinessException(ErrorCode.UNEXPECT_ERROR);
            }
        }
        return count;
    }


    @Override
    public File getFile(String id) {
        Optional<FileUpload> opt = repository.findOne((r, q, b) -> b.equal(r.get("id"), id));
        if (!opt.isPresent()) {
            return null;
        }
        FileUpload fileUpload = opt.orElse(null);
        String dir = getFileDir(String.valueOf(fileUpload.getBusinessType()), fileUpload.getUploadTime());
        return new File(String.format(FILE_FORMAT_PATTERN, fileDir, dir, fileUpload.getFilename()));
    }

    /**
     * 拼接文件名和路径。
     *
     * @param dir 目录。
     * @param filename 文件名。
     *
     * @return 文件。
     */
    private File getFile(String dir, String filename) {
        return new File(String.format(FILE_FORMAT_PATTERN, fileDir, dir, filename));
    }

}
