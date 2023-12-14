package com.sheepfly.media.dataaccess.dao;

import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import com.sheepfly.media.dataaccess.vo.ResourceVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAccessTestConfiguration.class)
public class ResourceMapperTest {
    @Autowired
    private ResourceMapper mapper;

    @Test
    public void selectResourceVoList() {
        List<ResourceVo> list = mapper.selectResourceVoList(null);
        list.forEach(System.out::println);
    }
}
