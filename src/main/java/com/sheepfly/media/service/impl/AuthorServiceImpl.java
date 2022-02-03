package com.sheepfly.media.service.impl;

import com.sheepfly.media.entity.Author;
import com.sheepfly.media.dao.AuthorMapper;
import com.sheepfly.media.service.IAuthorService;
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
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements IAuthorService {

}
