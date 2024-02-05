package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.dataaccess.vo.AlbumVo;
import com.sheepfly.media.service.base.AlbumResourceService;
import com.sheepfly.media.service.base.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/album")
@Slf4j
public class AlbumController {
    @Autowired
    private AlbumService service;
    @Autowired
    private AlbumResourceService arService;

    @PostMapping("/queryAlbumList")
    public TableResponse<AlbumVo> queryAlbumList(
            @RequestBody TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) {
        return service.queryAlbumList(tableRequest);
    }

    @PostMapping("/addAlbum")
    public ResponseData<Album> addAlbum(@RequestParam String albumName) {
        Album album = new Album();
        album.setName(albumName);
        if (service.checkRepeat(album)) {
            return ResponseData.fail(ErrorCode.ALBUM_REPEATED_ALBUM);
        }
        album.setCreateTime(new Date());
        Album save = service.save(album);
        return ResponseData.success(save);
    }
}
