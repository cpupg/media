package com.sheepfly.media.application;

import com.sheepfly.media.service.base.DirectoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTestConfigurationTest {
    @Autowired
    private DirectoryService service;

    @Test
    public void f() {
        System.out.println(service);
    }
}
