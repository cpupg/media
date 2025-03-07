package com.sheepfly.media.web.controller;


import com.sheepfly.media.common.constant.Constant;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.common.form.data.AuthorData;
import com.sheepfly.media.common.form.param.AuthorParam;
import com.sheepfly.media.common.http.ResponseData;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.common.vo.AuthorVo;
import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.service.base.IAuthorService;
import com.sheepfly.media.service.base.ISiteService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * <p>
 * 创作人员 前端控制器
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private IAuthorService service;
    @Autowired
    private ISiteService siteService;

    /**
     * 新增作者。
     *
     * <p>每个作者都必须有注册网站，同时userId和username必须二选一，如果都没有会抛出异常。</p>
     *
     * @param authorData 作者数据。
     *
     * @return 新作者。
     */
    @PostMapping("/add")
    public ResponseData<Author> add(@RequestBody @Validated AuthorData authorData)
            throws InvocationTargetException, IllegalAccessException {
        LOGGER.info("保存作者");
        String siteId = authorData.getSiteId();
        if (StringUtils.isEmpty(siteId) || !siteService.existsById(siteId)) {
            return ResponseData.fail(ErrorCode.AUTHOR_SITE_CANT_BE_NULL);
        }
        Author author = new Author();
        BeanUtils.copyProperties(author, authorData);
        author.setCreateTime(new Date());
        Author savedAuthor = service.save(author);
        return ResponseData.success(savedAuthor);
    }

    @GetMapping("/delete")
    public ResponseData<Author> delete(@RequestParam("id") String id) throws BusinessException {
        LOGGER.info("删除作者");
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.AUTHOR_ID_CANT_BE_NULL);
        }
        if (service.isAuthorCanBeDelete(id)) {
            service.safeLogicDeleteById(id, Author.class, ErrorCode.DELETE_NOT_EXIST_DATA);
            LOGGER.info("删除完成");
            return ResponseData.success();
        } else {
            LOGGER.info("要删除的作者不存在");
            return ResponseData.fail(ErrorCode.AUTHOR_ASSOCIATE_RESOURCE);
        }
    }

    @PostMapping("/queryList")
    public TableResponse<AuthorVo> queryList(
            @RequestBody TableRequest<AuthorParam, AuthorParam, AuthorParam> vo) throws BusinessException {
        AuthorParam params = vo.getParams();
        String username = params.getUsername();
        if (username != null && !StringUtils.isBlank(username)) {
            // todo spring security
            username = username.replace(Constant.SQL_LIKE, Constant.BLANK_STRING);
            params.setUsername(Constant.SQL_LIKE + username + Constant.SQL_LIKE);
        }
        return service.queryForAuthorList(vo);
    }
}

