package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.vo.AlbumVo;
import com.sheepfly.media.service.base.AlbumResourceService;
import com.sheepfly.media.service.base.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
@Slf4j
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumResourceService arService;

    @PostMapping("/queryAlbumList")
    public TableResponse<AlbumVo> queryAlbumList(@RequestBody AlbumParam albumParam) {
        return null;
    }
}
