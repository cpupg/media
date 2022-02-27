package com.sheepfly.media.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.querylist.ResourceVoForm;
import com.sheepfly.media.vo.ResourceVo;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询资源。
     *
     * @param form 查询条件
     *
     * @return 资源列表。
     */
    List<ResourceVo> selectResourceVoList(ResourceVoForm form);
}
