package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.ResourceCollectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ResourceCollect表的mapper。
 *
 * @author chen
 */
@Mapper
public interface ResourceCollectMapper {
    List<ResourceCollectVo> queryAll(TableRequest<BaseFilterVo, ResourceCollectVo, BaseSortVo> tableRequest);
}




