package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.CollectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Collect表的mapper。
 *
 * @author chen
 */
@Mapper
public interface CollectMapper {
    /**
     * 根据条件查询全部。
     *
     * @param tableRequest 查询参数。
     *
     * @return 查询结果。
     */
    List<CollectVo> queryAll(TableRequest<BaseFilterVo, CollectVo, BaseSortVo> tableRequest);
}




