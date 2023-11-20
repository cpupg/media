package com.sheepfly.media.service.impl;

import com.sheepfly.media.dataaccess.entity.Directory;
import com.sheepfly.media.dataaccess.entity.Directory_;
import com.sheepfly.media.dataaccess.repository.DirectoryRepository;
import com.sheepfly.media.service.ServiceTestConfiguration;
import com.sheepfly.media.service.base.DirectoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceTestConfiguration.class)
public class DirectoryServiceImplTest {
    @Autowired
    private DirectoryService service;
    @Autowired
    private DirectoryRepository repository;

    @Test
    public void testAddDirectory() throws Exception {
        String path = "c:/media/service/src/main/java/";
        String path2 = "C:/media/service/src/main/";
        Directory directory = service.createDirectory(path);
        Assert.assertEquals("目录层级错误", 5L, (long) directory.getLevel());
        Long code = directory.getParentCode();
        Optional<Directory> opt = repository.findOne((r, q, b) -> b.equal(r.get(Directory_.DIR_CODE), code));
        Assert.assertTrue("父目录不存在", opt.isPresent());
        Assert.assertEquals(path2, opt.orElse(null).getPath());
    }
}
