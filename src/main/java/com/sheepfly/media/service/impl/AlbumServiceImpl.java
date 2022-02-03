package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.Album;
import com.sheepfly.media.dao.AlbumMapper;
import com.sheepfly.media.service.IAlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-04
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {

}
