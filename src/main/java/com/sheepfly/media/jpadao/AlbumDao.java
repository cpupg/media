package com.sheepfly.media.jpadao;

import com.sheepfly.media.entity.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumDao extends PagingAndSortingRepository<Album, Character> {
}
