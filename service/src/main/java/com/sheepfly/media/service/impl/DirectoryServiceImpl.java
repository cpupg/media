package com.sheepfly.media.service.impl;

import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.form.filter.DirectoryFilter;
import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.dataaccess.vo.DirectoryVo;
import com.sheepfly.media.service.base.DirectoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    private DirectoryRepository repository;

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
}
