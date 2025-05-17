package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.common.form.param.TagReferenceParam;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.util.ObjectUtil;
import com.sheepfly.media.common.vo.TagReferenceVo;
import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = DataAccessTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TagReferenceMapperTest {
    @Autowired
    private TagReferenceMapper mapper;
    @Test
    public void test_queryTagReferenceList() {
        TagReferenceParam param = ObjectUtil.createBean(TagReferenceParam.class);
        TableRequest<Object, TagReferenceParam, Object> tableRequest = new TableRequest<>();
        tableRequest.setParams(param);
        List<TagReferenceVo> list = mapper.queryTagReferenceList(tableRequest);
        assertTrue(list.isEmpty());
    }
}
