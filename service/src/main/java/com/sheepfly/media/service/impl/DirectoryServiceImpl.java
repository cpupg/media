package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.form.filter.DirectoryFilter;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.dataaccess.vo.DirectoryVo;
import com.sheepfly.media.service.base.DirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    private DirectoryRepository repository;
    @Autowired
    private Snowflake snowflake;

    @Override
    public List<Directory> queryDirectoryList(DirectoryFilter filter) {
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
    public Directory addDirectory(String path) {
        log.info("目标目录{}", path);
        Optional<Directory> op = repository.findOne((root, query, builder) -> builder.equal(root.get("path"), path));
        if (op.isPresent()) {
            return op.orElse(null);
        }
        // 将目录以/分隔，从最后一层依次查找目录表，查到后作为父目录依次简历后面的目录
        // 假设目标目录是/a/b/c/d/e/f/,库里保存的目录是/a/b/c
        String[] dirNames = path.split(Constant.SEPERATOR);
        Directory rootDirectory = new Directory();
        // 第一个元素是空串
        rootDirectory.setPath(Constant.SEPERATOR + dirNames[1] + Constant.SEPERATOR);
        Example<Directory> example = Example.of(rootDirectory);
        Optional<Directory> optRootDir = repository.findOne(example);
        if (!optRootDir.isPresent()) {
            log.warn("根目录{}不存在", dirNames[1]);
            rootDirectory.setId(snowflake.nextIdStr());
            rootDirectory.setName(dirNames[1]);
            rootDirectory.setLevel(1);
            rootDirectory.setDirCode(createDirCode());
            rootDirectory.setCodeList(0 + "." + rootDirectory.getDirCode());
            rootDirectory.setCreateTime(new Date());
            rootDirectory.setParentCode(0L);
            rootDirectory.setDeleteStatus(Constant.NOT_DELETED);
            Directory save = repository.save(rootDirectory);
            log.info("根目录创建完成{}", save);
        }
        // 当前查询的目录，从后往前依次查询
        String currentPath = path;
        // 返回用的目录
        Directory resultDir = null;
        // todo 优化搜索算法
        for (int i = dirNames.length - 1; i > 0; i--) {
            String dirName = dirNames[i];
            // 搜索/a/b/c/d/e/f/对应的目录
            Directory currentDir = queryDirectoryByPath(currentPath);
            if (currentDir != null) {
                // 搜到父目录/a/b/c/，此时dirName=d
                log.info("找到当前目录，开始创建子目录:{}", currentDir);
                StringBuilder codeListBuilder = new StringBuilder(currentDir.getCodeList());
                // 当前目录的层级就是子目录的下标
                // 目录：/a/b/c/d/e
                // 层级：/1/2/3/4/5
                // 下表：/0/1/2/3/4
                // 使用split分割后，下标0是空串，计算时下标要+1
                StringBuilder pathBuilder = new StringBuilder(currentDir.getPath());
                long parentCode = currentDir.getDirCode();
                for (int j = currentDir.getLevel() + 1; j < dirNames.length; j++) {
                    pathBuilder.append(dirNames[j]).append(Constant.SEPERATOR);
                    long code = createDirCode();
                    codeListBuilder.append(".").append(code);
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
                    resultDir = repository.save(subDir);
                    log.info("子目录创建成功：{}", resultDir);
                    parentCode = subDir.getDirCode();
                }
                return currentDir;
            }
            // 没找到父目录，currentPath=/a/b/c/d/e/f/，从后往前截取，直到变成/a/b/c/
            currentPath = currentPath.substring(0, currentPath.length() - 1 - dirName.length());
        }
        return resultDir;
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
     * 创建目录代码。
     *
     * <p>使用库表中的最大目录代码+1</p>
     *
     * <p>目录代码列表长度是100，可以放下多层目录</p>
     *
     * @return 目录代码。
     */
    private Long createDirCode() {
        if (repository.count() == 0) {
            // todo 只有第一次使用系统会用到，需要优化
            return 1L;
        }
        // 目录代码是唯一的，查询时不能加条件
        Directory directory = repository.findFirstByOrderByDirCodeDesc();
        log.info("当前最大目录代码:" + directory.getDirCode());
        return directory.getDirCode() + 1;
    }
}
