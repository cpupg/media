package com.sheepfly.media.service.base;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.param.DirectoryParam;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.common.vo.DirectoryVo;

import java.util.List;

/**
 * 目录服务。
 *
 * @author wrote-code
 */
public interface DirectoryService {
    List<Directory> queryDirectoryList(DirectoryParam filter);

    /**
     * 根据目录层级和父目录来查询目录。
     *
     * @return 目录。
     */
    List<DirectoryVo> queryDirectoryList();

    /**
     * 添加目录。
     *
     * <p>调用此方法前必须手动判断目录是否存在，不能直接调用此方法，否则会重复创建目录，此外，
     * 传入的目录必须是绝对路径且必须以斜杠/结尾，如果不是绝对路径，会抛出异常。所有添加的
     * 目录的分隔符都是/，且只能是带盘符的windows路径。</p>
     *
     * <p>系统中已经内置根目录/（需要提前手动插入），否则运行会出现问题。</p>
     *
     * <p>注意，此方法有可能返回null，因此在调用时需要判空，也可以不判空让其抛出异常。</p>
     *
     * @param path 目录。
     *
     * @return 入库的目录。
     */
    Directory createDirectory(String path) throws BusinessException;

    /**
     * 通过目录路径查询目录。
     *
     * <p>目录必须是带盘符的win目录且以斜杠/结尾，否则及时存在目录也会返回空。</p>
     *
     * @param path 路径。
     *
     * @return 匹配的目录。
     */
    Directory queryDirectoryByPath(String path);
}
