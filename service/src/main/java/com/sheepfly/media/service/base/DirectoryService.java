package com.sheepfly.media.service.base;

import com.sheepfly.media.common.exception.BusinessException;
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
     * 添加目录。
     *
     * <p>调用此方法前必须手动判断目录是否存在，不能直接调用此方法，因为此方法内部是通过遍历
     * 目录层级来添加目录。传入的目录必须是绝对路径，如果不是绝对路径，会抛出异常。所有添加的
     * 目录的分隔符都是/，如果输入的是反斜杠\，则会转化为斜杠。</p>
     *
     * <p>系统中已经内置根目录/（需要提前手动插入），否则运行会出现问题。</p>
     *
     * @param path 目录。
     *
     * @return 入库的目录。
     */
    Directory addDirectory(String path) throws BusinessException;

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
