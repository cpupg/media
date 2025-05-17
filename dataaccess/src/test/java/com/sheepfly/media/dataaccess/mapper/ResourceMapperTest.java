package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.filter.ResourceFilter;
import com.sheepfly.media.common.form.param.ResourceParam;
import com.sheepfly.media.common.form.sort.ResourceSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.util.ObjectUtil;
import com.sheepfly.media.common.vo.ResourceVo;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import org.junit.Assert;
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
    public void testSelectResourceVoList2() {
        TableRequest<ResourceFilter, ResourceParam, ResourceSort> form = new TableRequest<>();
        ResourceParam params = ObjectUtil.createBean(ResourceParam.class);
        params.setTagNames(new String[]{});
        form.setParams(params);
        List<ResourceVo> lists = mapper.selectResourceVoList(form);
        Assert.assertTrue(lists.isEmpty());
    }

    @Test
    public void testSelectTagReferenceByResourceId() {
        List<TagReferenceVo> list = mapper.selectTagReferenceByResourceId(ObjectUtil.getDefaultValue(String.class));
        Assert.assertTrue((list.isEmpty()));
    }
}
