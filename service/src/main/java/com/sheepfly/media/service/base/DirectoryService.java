package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.DirectoryFilter;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.vo.DirectoryVo;

import java.util.List;

/**
 * 目录服务。
 *
 * @author wrote-code
 */
public interface DirectoryService {
    List<Directory> queryDirectoryList(DirectoryFilter filter);

    /**
     * 根据目录层级和父目录来查询目录。
     *
     * @return 目录。
     */
    List<DirectoryVo> queryDirectoryList();
}
