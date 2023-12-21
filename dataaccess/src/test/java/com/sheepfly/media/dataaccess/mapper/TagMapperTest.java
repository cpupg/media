package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import com.sheepfly.media.dataaccess.vo.TagVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = DataAccessTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TagMapperTest {
    @Autowired
    private TagMapper mapper;

    @Test
    public void testQueryTagListByName() {
        List<TagVo> list = mapper.queryTagListByName("测试");
        System.out.println(list.size());
        list.forEach(System.out::println);
    }

}
