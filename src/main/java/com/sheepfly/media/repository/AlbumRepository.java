package com.sheepfly.media.repository;

import com.sheepfly.media.entity.Album;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Character> {
}
