package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.vo.CollectVo;
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
public class CollectMapperTest {
    @Resource
    private CollectMapper collectMapper;

    @Test
    public void test_queryAll() {
        CollectVo vo = new CollectVo();
        vo.setCollectName("hello");
        vo.setResourceId("123456");
        TableRequest tableRequest = new TableRequest();
        tableRequest.setParams(vo);
        List<CollectVo> list = collectMapper.queryAll(tableRequest);
        Assert.assertTrue(list.isEmpty());
    }
}
