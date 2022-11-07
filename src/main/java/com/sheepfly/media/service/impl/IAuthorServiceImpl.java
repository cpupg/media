package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.Author;
import com.sheepfly.media.repository.AuthorRepository;
import com.sheepfly.media.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 作者服务实现。
 *
 * @author wrote-code
 * @since 2022.10.17 v0.0.1-SNAPSHOT
 */
@Service
public class IAuthorServiceImpl extends BaseJpaServiceImpl<Author, String, AuthorRepository> implements IAuthorService {
    @Autowired
    private AuthorRepository repository;

}
