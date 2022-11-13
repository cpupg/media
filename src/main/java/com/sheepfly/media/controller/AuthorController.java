package com.sheepfly.media.controller;


import com.sheepfly.media.entity.Author;
import com.sheepfly.media.form.data.AuthorData;
import com.sheepfly.media.service.IAuthorService;
import com.sheepfly.media.util.BeanUtil;
import com.sheepfly.media.util.ValidateUtil;
import com.sheepfly.media.vo.common.ErrorCode;
import com.sheepfly.media.vo.common.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

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
    public ResponseData<Author> add(@RequestBody AuthorData authorData) {
        log.info("保存作者");
        if (ValidateUtil.isEmptyString(authorData.getSiteId())) {
            return ResponseData.fail(ErrorCode.AUTHOR_SITE_CANT_BE_NULL);
        }
        if (ValidateUtil.isEmptyString(authorData.getUserId()) && ValidateUtil.isEmptyString(
                authorData.getUsername())) {
            return ResponseData.fail(ErrorCode.AUTHOR_ID_AND_NAME_CANT_NULL);
        }
        // todo 验证网站是否存在
        Author author = BeanUtil.dataToEntity(authorData, Author.class);
        author.setCreateTime(LocalDate.now());
        Author savedAuthor = service.save(author);
        return ResponseData.success(savedAuthor);
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseData<Author> delete(@RequestParam("id") String id) {
        log.info("删除作者");
        if (service.existsById(id)) {
            service.deleteById(id);
            log.info("删除完成");
            return ResponseData.success();
        } else {
            log.info("要删除的作者不存在");
            return ResponseData.fail(ErrorCode.DELETE_NOT_EXIST_DATA);
        }
    }
}

