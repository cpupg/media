package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.BaseFilterVo;
import com.sheepfly.media.common.vo.BaseSortVo;
import com.sheepfly.media.common.vo.ResourceCollectVo;
import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAccessTestConfiguration.class)
public class ResourceCollectMapperTest {
    @Resource
    private ResourceCollectMapper mapper;

    @Test
    public void testQueryAll() {
        ResourceCollectVo vo = new ResourceCollectVo();
        vo.setCollectName("hello");
        TableRequest<BaseFilterVo, ResourceCollectVo, BaseSortVo> tableRequest = new TableRequest<>(null, vo, null);
        List<ResourceCollectVo> list = mapper.queryAll(tableRequest);
        Assert.assertTrue(list.isEmpty());
    }
}
