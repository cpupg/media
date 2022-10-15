package com.sheepfly.media.dao;

import com.sheepfly.media.form.querylist.ResourceVoForm;
import com.sheepfly.media.vo.ResourceVo;
import org.apache.ibatis.annotations.Mapper;

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
    List<ResourceVo> selectResourceVoList(ResourceVoForm form);
}
