package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.param.DirectoryParam;
import com.sheepfly.media.common.vo.DirectoryVo;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.service.base.DirectoryService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DirectoryServiceImpl implements DirectoryService, InitializingBean {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DirectoryServiceImpl.class);
    @Autowired
    private DirectoryRepository repository;
    @Autowired
    private Snowflake snowflake;

    @Override
    public List<Directory> queryDirectoryList(DirectoryParam filter) {
        return null;
    }


    @Override
    public List<DirectoryVo> queryDirectoryList() {
        List<Directory> directories = repository.queryByLevelAndDeleteStatus(0, Constant.NOT_DELETED);
        List<DirectoryVo> voList = new ArrayList<>();
        // 第一层目录。
        for (Directory directory : directories) {
            DirectoryVo directoryVo = new DirectoryVo();
            BeanUtils.copyProperties(directory, directoryVo);
            voList.add(directoryVo);
            // 第二层目录
            List<Directory> childrenDirectoryList = repository.queryByParentCodeAndDeleteStatus(directory.getDirCode(),
                    Constant.NOT_DELETED);
            List<DirectoryVo> childrenVoList = new ArrayList<>();
            for (Directory childrenDir : childrenDirectoryList) {
                DirectoryVo vo = new DirectoryVo();
                BeanUtils.copyProperties(childrenDir, vo);
                childrenVoList.add(vo);
            }
            directoryVo.setChildren(childrenVoList);
        }
        return voList;
    }

    @Override
    public Directory createDirectory(String path) throws BusinessException {
        String pathPrefix = FilenameUtils.getPrefix(path).toUpperCase();
        Directory driver = createDriver(pathPrefix);
        LOGGER.info("盘符{},目录代码:{}", driver.getPath(), driver.getDirCode());
        // 将目录以/分隔，从最后一层依次查找目录表，查到后作为父目录依次简历后面的目录
        // 假设目标目录是c:/a/b/c/d/e/f/,系统会按层级依次保存c:,a,b,c,d,e,f等7个目录。
        String[] dirNames = path.split(Constant.SEPERATOR);
        // 当前查询的目录，从后往前依次查询
        path = pathPrefix + path.substring(3);
        // todo 优化搜索算法
        for (int i = dirNames.length - 1; i >= 0; i--) {
            String dirName = dirNames[i];
            // 搜索/a/b/c/d/e/f/对应的目录
            Directory currentDir = queryDirectoryByPath(path);
            if (currentDir != null) {
                // 搜到父目录/a/b/c/，此时dirName=d
                LOGGER.info("找到当前目录，开始创建子目录:{}", currentDir);
                StringBuilder codeListBuilder = new StringBuilder(currentDir.getCodeList());
                // 当前目录的层级就是子目录的下标
                // 目录：c:/a/b/c/d/e
                // 层级：0/1/2/3/4/5
                // 下标：0/1/2/3/4/5
                // 使用split分割后，下标0是空串，计算时下标要+1
                StringBuilder pathBuilder = new StringBuilder(currentDir.getPath());
                long parentCode = currentDir.getDirCode();
                Directory resultDir = null;
                for (int j = currentDir.getLevel() + 1; j < dirNames.length; j++) {
                    pathBuilder.append(dirNames[j]).append(Constant.SEPERATOR);
                    long code = createDirCode();
                    codeListBuilder.append(".").append(Long.toHexString(code).toUpperCase());
                    Directory subDir = new Directory();
                    subDir.setId(snowflake.nextIdStr());
                    subDir.setDirCode(code);
                    subDir.setParentCode(parentCode);
                    subDir.setName(dirNames[j]);
                    subDir.setPath(pathBuilder.toString());
                    subDir.setCodeList(codeListBuilder.toString());
                    subDir.setLevel(j);
                    subDir.setDeleteStatus(Constant.NOT_DELETED);
                    subDir.setCreateTime(new Date());
                    resultDir = repository.saveAndFlush(subDir);
                    LOGGER.info("子目录创建成功：{}", resultDir);
                    parentCode = subDir.getDirCode();
                }
                return resultDir;
            }
            // 没找到父目录，currentPath=/a/b/c/d/e/f/，从后往前截取，直到变成/a/b/c/
            path = path.substring(0, path.length() - 1 - dirName.length());
        }
        return null;
    }

    @Override
    public Directory queryDirectoryByPath(String path) {
        Directory directory = new Directory();
        directory.setDeleteStatus(Constant.NOT_DELETED);
        directory.setPath(path);
        // 全路径是唯一的
        return repository.findOne(Example.of(directory)).orElse(null);
    }

    /**
     * 创建盘符目录。
     *
     * <p>传入的值应该是全路径的前缀，比如c:，且没有斜杠。</p>
     *
     * @param driverPath 盘符，比如c:。
     *
     * @return 盘符目录。
     */
    private Directory createDriver(String driverPath) {
        Directory directory = new Directory();
        directory.setPath(driverPath);
        Example<Directory> example = Example.of(directory);
        Optional<Directory> opt = repository.findOne(example);
        if (opt.isPresent()) {
            return opt.orElse(null);
        } else {
            directory.setId(snowflake.nextIdStr());
            directory.setDirCode(createDriverCode());
            directory.setParentCode(0L);
            directory.setName(driverPath);
            directory.setPath(driverPath);
            directory.setCodeList(String.valueOf(directory.getDirCode()));
            directory.setLevel(0);
            directory.setDeleteStatus(Constant.NOT_DELETED);
            directory.setCreateTime(new Date());
            return repository.saveAndFlush(directory);
        }
    }

    /**
     * 创建目录代码。
     *
     * <p>使用库表中的最大目录代码+1</p>
     *
     * <p>目录代码列表长度是100，可以放下多层目录</p>
     *
     * @return 目录代码。
     */
    private Long createDirCode() {
        // 目录代码是唯一的，查询时不能加条件
        Directory directory = repository.findFirstByOrderByDirCodeDesc();
        LOGGER.info("当前最大目录代码:" + directory.getDirCode());
        return directory.getDirCode() + 1;
    }

    /**
     * 创建盘符对应的目录代码。
     *
     * <p>盘符的目录代码小于0。</p>
     *
     * @return 目录代码。
     */
    private Long createDriverCode() {
        Directory directory = repository.findFirstByOrderByDirCode();
        LOGGER.info("当前最大盘符代码：{}", Math.abs(directory.getDirCode()));
        return directory.getDirCode() - 1;
    }

    @Override
    public void afterPropertiesSet() throws BusinessException {
        LOGGER.info("初始化根目录");
        Directory root = new Directory();
        root.setPath(Constant.SEPERATOR);
        Example<Directory> example = Example.of(root);
        Optional<Directory> opt = repository.findOne(example);
        if (!opt.isPresent()) {
            LOGGER.warn("没有根目录");
            root.setId(snowflake.nextIdStr());
            root.setDirCode(0L);
            root.setParentCode(0L);
            root.setName("/");
            root.setPath("/");
            root.setCodeList("0");
            root.setLevel(0);
            root.setDeleteStatus(Constant.NOT_DELETED);
            root.setCreateTime(new Date());
            repository.saveAndFlush(root);
        }
        LOGGER.info("初始化成功");
    }
}
