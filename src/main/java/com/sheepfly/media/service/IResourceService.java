package com.sheepfly.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.form.querylist.ResourceVoForm;
import com.sheepfly.media.vo.ResourceVo;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IResourceService extends IService<Resource> {
    /**
     * 查询资源。
     *
     * @param form 查询表单。
     *
     * @return 满足条件的资源。
     */
    List<ResourceVo> queryResourceVoList(ResourceVoForm form);
}
