package com.sheepfly.media.service.base;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.FileUpload;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件服务。
 *
 * <p>所有文件操作都在一个控制器和服务种操作，不需要继承BaseJpaService接口。</p>
 */
public interface FileService {
    /**
     * 开始上传。
     */
    int START_UPLOAD = 1;
    /**
     * 上传完成。
     */
    int AFTER_UPLOAD = 2;

    /**
     * 上传文件。
     *
     * @param file 上传的文件。
     * @param businessCode 业务代码
     * @param businessType 业务类型
     *
     * @return 文件信息。
     */
    FileInfo uploadFile(MultipartFile file, String businessCode, String businessType) throws IOException;

    /**
     * 根据业务代码查询上传的文件。
     *
     * @param businessCode 业务代码。
     *
     * @return 文件列表。
     */
    TableResponse<FileInfo> queryFileList(String businessCode);

    /**
     * 根据id获取文件对象。
     *
     * @param id 文件id。
     *
     * @return 文件对象。
     */
    File getFile(String id);

    /**
     * 获取文件目录。
     *
     * <p>文件目录=根目录/业务类型/年份/月份/日/时间戳文件名</p>
     *
     * @param businessType 业务类型。
     * @param date 上传时间。
     *
     * @return 全路径。
     */
    String getFileDir(String businessType, Date date);

    /**
     * 删除文件。
     *
     * @param id 文件标识。
     *
     * @return 被删除的文件。
     */
    FileUpload deleteFile(String id) throws BusinessException;

    /**
     * 获取业务类型。
     *
     * <p>业务类新在business-type.properties中。</p>
     *
     * @param key key
     *
     * @return 业务类型。
     */
    String getBusinessType(String key);

    /**
     * 删除业务代码下的所有文件。
     *
     * <p>删除时先修改数据库，然后才删除，如果出错，则已删除文件不会恢复，但数据库状态会还原。</p>
     *
     * @param businessCode 业务代码。
     *
     * @return 删除结果。
     *
     * @throws BusinessException e。
     */
    int deleteFileByBusinessCode(String businessCode) throws BusinessException;

}
