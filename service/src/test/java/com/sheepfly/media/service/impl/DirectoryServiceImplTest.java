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
        String path = "/media/service/src/main/java/";
        Directory directory = service.addDirectory("/media/service/src/main/java/");
        Assert.assertEquals(path, directory.getPath());
        Assert.assertEquals(5, (long) directory.getLevel());
        Optional<Directory> opt = repository.findOne(
                (root, query, builder) -> builder.equal(root.get(Directory_.PARENT_CODE), directory.getParentCode()));
        Assert.assertEquals(opt.orElse(null).getPath(), "/media/service/src/main/java/");
    }
}
