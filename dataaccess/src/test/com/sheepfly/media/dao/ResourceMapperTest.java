package com.sheepfly.media.dao;

import com.sheepfly.media.vo.ResourceVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceMapperTest {
    @Autowired
    private ResourceMapper mapper;

    @Test
    public void selectResourceVoList() {
        List<ResourceVo> list = mapper.selectResourceVoList(null);
        list.forEach(System.out::println);
    }
}