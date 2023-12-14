package com.sheepfly.media.cli.task.impl;

import com.sheepfly.media.cli.CliTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = CliTestConfiguration.class)
@RunWith(SpringRunner.class)
public class TransFormDirectoryTaskImplTest {
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Test
    public void testNpJdbcTemplate() {
        String sql = "select FILENAME from media.resource limit :limit offset :offset";
        Map<String, Integer> map = new HashMap<>();
        map.put("limit", 0);
        map.put("offset", 20);
        List<Map<String, Object>> list = npJdbcTemplate.queryForList(sql, map);
        System.out.println(list);
    }
}
