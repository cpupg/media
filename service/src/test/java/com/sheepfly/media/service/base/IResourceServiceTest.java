package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.TagReference;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import com.sheepfly.media.service.ServiceTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ServiceTestConfiguration.class)
@RunWith(SpringRunner.class)
public class IResourceServiceTest {
    @Autowired
    private IResourceService service;

    @Autowired

    @Test
    public void createAndDeleteTagReference() {
        ResourceParam filter = new ResourceParam();
        filter.setCurrent(1);
        filter.setPageSize(1);
        TableRequest<ResourceFilter, ResourceParam, ResourceSort> req = new TableRequest<>();
        req.setParams(filter);
        TableResponse<ResourceVo> obj = service.queryResourceVoList(req);
        ResourceVo resourceVo = obj.getData().get(0);
        TagReference tagReference = service.createResourceTag(resourceVo.getId(), "测试用例");
        System.out.println(tagReference);
        service.deleteResourceTag(tagReference.getId());
    }
}
