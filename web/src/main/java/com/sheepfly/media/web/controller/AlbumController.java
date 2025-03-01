package com.sheepfly.media.web.controller;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.AlbumVo;
import com.sheepfly.media.dataaccess.entity.Album;
import com.sheepfly.media.service.base.AlbumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService service;

    @PostMapping("/queryAlbumList")
    public TableResponse<AlbumVo> queryAlbumList(
            @RequestBody TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest) throws BusinessException {
        AlbumParam params = tableRequest.getParams();
        if (params.isQueryWithResource() && StringUtils.isEmpty(params.getResourceId())) {
            throw new BusinessException(ErrorCode.ALBUM_SELECT_MODAL_LOST_RESOURCE);
        }
        return service.queryAlbumList(tableRequest);
    }

    @PostMapping("/addAlbum")
    public ResponseData<Album> addAlbum(@RequestParam String albumName, @RequestParam("coverId") String coverId)
            throws BusinessException {
        if (StringUtils.isEmpty(albumName)) {
            throw new BusinessException(ErrorCode.ALBUM_EMPTY_NAME);
        }
        if (albumName.length() > 32) {
            throw new BusinessException(ErrorCode.TAG_NAME_TOO_LONG);
        }
        Album album = new Album();
        album.setName(albumName);
        if (service.checkRepeat(album)) {
            return ResponseData.fail(ErrorCode.ALBUM_REPEATED_ALBUM);
        }
        album.setCreateTime(new Date());
        if (!StringUtils.isEmpty(coverId)) {
            // todo 检查文件是否存在
            album.setCoverId(coverId);
        }
        Album save = service.save(album);
        return ResponseData.success(save);
    }
}
