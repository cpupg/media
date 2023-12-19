package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.mapper.DirRepoMapper;
import com.sheepfly.media.dataaccess.entity.DirRepo;
import com.sheepfly.media.dataaccess.repository.DirRepoRepository;
import com.sheepfly.media.dataaccess.vo.DirRepoVo;
import com.sheepfly.media.service.base.DirRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DirRepoServiceImpl extends BaseJpaServiceImpl<DirRepo, String, DirRepoRepository>
        implements DirRepoService {
    @Autowired
    private DirRepoRepository repository;
    @Autowired
    private DirRepoMapper mapper;

    @Override
    public List<DirRepoVo> queryAllDirRepoList() {
        // 目前查全部就可以，暂时不需要设置过滤条件。
        return mapper.queryAll();
    }

    @Override
    public long createDirCode() {
        DirRepo dirRepo = repository.queryFirstByOrderByDirCodeDesc();
        if (dirRepo == null) {
            return 1;
        } else {
            return dirRepo.getDirCode() + 1;
        }
    }
}
