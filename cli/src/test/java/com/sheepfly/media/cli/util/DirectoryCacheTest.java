package com.sheepfly.media.cli.util;

import com.sheepfly.media.cli.CliTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CliTestConfiguration.class)
@RunWith(SpringRunner.class)
public class DirectoryCacheTest {
    @Autowired
    private DirectoryCache cache;

    @Test
    public void testGet() {
        System.out.println(cache.get("c:/users"));
        System.out.println(cache.get("/"));
        System.out.println(cache.get("/"));
    }
}
