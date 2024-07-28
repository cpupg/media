package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.vo.DirRepoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DirRepoMapper {
    List<DirRepoVo> queryAll();
}
