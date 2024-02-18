package com.sheepfly.media.service.base;

import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.FileUpload;
import com.sheepfly.media.dataaccess.vo.file.FileInfo;

import java.util.List;

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
     * <p>一个文件需要上传两次，第一次是上传文件，第二次是修改文件状态。</p>
     *
     * @param fileUploadInfo 文件信息。
     *
     * @return 文件信息。
     */
    FileInfo uploadFile(FileUpload fileUploadInfo);

    /**
     * 根据业务代码查询上传的文件。
     *
     * @param businessCode 业务代码。
     *
     * @return 文件列表。
     */
    TableResponse<List<FileInfo>> queryFileList(String businessCode);

}
