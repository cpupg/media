package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.dataaccess.vo.TagReferenceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Mapper
public interface ResourceMapper {
    /**
     * 查询资源。
     *
     * @param form 查询条件
     *
     * @return 资源列表。
     */
    List<ResourceVo> selectResourceVoList(ProComponentsRequestVo<ResourceFilter, ResourceFilter, Object> form);

    List<TagReferenceVo> selectTagReferenceByResourceId(String resourceId);

    List<TagReferenceVo> queryTagReferenceByResourceIdAndCount(@Param("resourceId") String resourceId,
            @Param("limitCount") Integer limitCount);
}
