package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Resource;
import com.sheepfly.media.service.IResourceService;
import com.sheepfly.media.vo.ResourceVo;
import com.sheepfly.media.vo.common.ProTableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Controller
@RequestMapping(value = "/resource", produces = "application/json;charset=utf-8")
public class ResourceController {
    @Autowired
    @Qualifier("resourceServiceImpl")
    private IResourceService service;

    @PostMapping("/queryResourceList")
    @ResponseBody
    public ProTableObject<ResourceVo> queryResourceList() {
        ResourceVo resourceVo = new ResourceVo();
        List<ResourceVo> list = new ArrayList<>();
        list.add(resourceVo);
        List<Resource> list1 = service.list(null);
        System.out.println(list1.toString());
        service.queryResourceVoList(null);
        return new ProTableObject<>(list.size(), list);
    }
}

