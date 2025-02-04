package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.DirRepo;
import com.sheepfly.media.dataaccess.repository.DirRepoRepository;
import com.sheepfly.media.common.vo.DirRepoVo;

import java.util.List;

public interface DirRepoService extends BaseJpaService<DirRepo, String, DirRepoRepository> {
    List<DirRepoVo> queryAllDirRepoList();

    /**
     * 生成目录代码。
     *
     * <p>生成规则：从1开始递增。如果还没有目录，则返回1，否则返回当前库表里的最大目录代码+1。</p>
     *
     * @return 目录代码。
     */
    long createDirCode();
}
