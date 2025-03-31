package com.sheepfly.media.common.util;

import com.sheepfly.media.common.vo.AlbumVo;
import com.sheepfly.media.common.vo.ResourceVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ObjectUtilTest {
    @Test
    void testTrim() {
        ResourceVo resourceVo = new ResourceVo();
        resourceVo.setId(UUID.randomUUID().toString());
        resourceVo.setFilename(" file ");
        resourceVo.setCoverId(" cid ");
        AlbumVo albumVo = new AlbumVo();
        albumVo.setCoverId(" cover ");
        albumVo.setName(" name ");
        resourceVo.setAlbumVo(albumVo);
        ObjectUtil.trim(resourceVo);
        Assertions.assertEquals("file", resourceVo.getFilename());
        Assertions.assertEquals("cid", resourceVo.getCoverId());
        Assertions.assertEquals("cover", albumVo.getCoverId());
        Assertions.assertEquals("name", albumVo.getName());
    }
}
