package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
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

    @Test
    public void testSelectResourceVoList2() {
        TableRequest<ResourceFilter, ResourceParam, ResourceSort> form = new TableRequest<>();
        ResourceParam params = new ResourceParam();
        params.setFilename("测试文件");
        params.setDir("C:/hello/");
        form.setParams(params);
        List<ResourceVo> lists = mapper.selectResourceVoList(form);
        System.out.println(lists);
    }

    @Test
    public void testSelectTagReferenceByResourceId() {
        List<TagReferenceVo> list = mapper.selectTagReferenceByResourceId("1735347059734609920");
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectTagReferenceByResourceIdAndLimitCount() {
        List<TagReferenceVo> list = mapper.queryTagReferenceByResourceIdAndCount("12345", 10);
        System.out.println(list.isEmpty());
    }
}
