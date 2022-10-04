package com.sheepfly.media.jpadao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AlbumDaoTest {
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void test() {
        System.out.println(albumDao);
        long count = albumDao.count();
        System.out.println(count);
    }
}