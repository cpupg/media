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

    /**
     * 添加目录，若目录已存在，则返回目录。
     *
     * <p>目录分隔符是/，如果传入的分隔符不是/，需要替换为/。</p>
     *
     * @param path 目录。
     *
     * @return 入库的目录。
     */
    Directory addDirectory(String path);

    /**
     * 通过目录路径查询目录。
     *
     * <p>目录分隔符是/，如果传入的分隔符不是/，需要替换为/。</p>
     *
     * <p>全路径应该以一个斜杠结尾，不能以多个斜杠或其他字符结尾。</p>
     *
     * @param path 路径。
     *
     * @return 匹配的目录。
     */
    Directory queryDirectoryByPath(String path);
}
