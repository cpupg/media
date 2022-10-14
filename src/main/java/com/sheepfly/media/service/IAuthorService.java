package com.sheepfly.media.service;

import com.sheepfly.media.entity.Author;
import com.sheepfly.media.repository.AuthorRepository;

/**
 * <p>
 * 创作人员 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IAuthorService extends BaseJpaService<Author, String, AuthorRepository> {

}
