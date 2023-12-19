package com.sheepfly.media.dataaccess.repository;

import com.sheepfly.media.dataaccess.DataAccessTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DataAccessTestConfiguration.class)
@RunWith(SpringRunner.class)
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;

    @Test
    public void f() {
        int i = repository.countBySiteId("12345");
        System.out.println(i);
    }
}
