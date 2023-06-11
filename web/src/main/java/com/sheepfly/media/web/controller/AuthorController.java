package com.sheepfly.media.controller;


import com.sheepfly.media.constant.Constant;
import com.sheepfly.media.entity.Author;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.data.AuthorData;
import com.sheepfly.media.form.filter.AuthorFilter;
import com.sheepfly.media.service.IAuthorService;
import com.sheepfly.media.service.ISiteService;
import com.sheepfly.media.vo.AuthorVo;
import com.sheepfly.media.exception.ErrorCode;
import com.sheepfly.media.http.ProComponentsRequestVo;
import com.sheepfly.media.http.ProTableObject;
import com.sheepfly.media.http.ResponseData;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/author")
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
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
    @ResponseBody
    public ResponseData<Author> add(@RequestBody @Validated AuthorData authorData)
            throws InvocationTargetException, IllegalAccessException {
        log.info("保存作者");
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
    @ResponseBody
    public ResponseData<Author> delete(@RequestParam("id") String id) throws BusinessException {
        log.info("删除作者");
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCode.AUTHOR_ID_CANT_BE_NULL);
        }
        if (service.isAuthorCanBeDelete(id)) {
            service.safeLogicDeleteById(id, Author.class, ErrorCode.DELETE_NOT_EXIST_DATA);
            log.info("删除完成");
            return ResponseData.success();
        } else {
            log.info("要删除的作者不存在");
            return ResponseData.fail(ErrorCode.AUTHOR_ASSOCIATE_RESOURCE);
        }
    }

    @PostMapping("/queryList")
    @ResponseBody
    public ProTableObject<AuthorVo> queryList(
            @RequestBody ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo) throws BusinessException {
        AuthorFilter params = vo.getParams();
        String username = params.getUsername();
        if (username != null && !StringUtils.isBlank(username)) {
            // todo spring security
            username = username.replace(Constant.SQL_LIKE, Constant.BLANK_STRING);
            params.setUsername(Constant.SQL_LIKE + username + Constant.SQL_LIKE);
        }
        return service.queryForAuthorList(vo);
    }
}

