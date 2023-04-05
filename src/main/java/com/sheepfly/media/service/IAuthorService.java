package com.sheepfly.media.service;

import com.sheepfly.media.entity.Author;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.form.filter.AuthorFilter;
import com.sheepfly.media.repository.AuthorRepository;
import com.sheepfly.media.vo.AuthorVo;
import com.sheepfly.media.vo.common.ProComponentsRequestVo;
import com.sheepfly.media.vo.common.ProTableObject;

/**
 * <p>
 * 创作人员 服务类
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
public interface IAuthorService extends BaseJpaService<Author, String, AuthorRepository> {
    /**
     * 查询作者清单。
     *
     * @param vo 参数。
     *
     * @return 查询结果。
     *
     * @throws BusinessException 业务异常。
     */
    ProTableObject<AuthorVo> queryForAuthorList(ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo)
            throws BusinessException;


    /**
     * 检查作者是否可以删除。
     *
     * @param id 作者id。
     *
     * @return 可以删除返回true。
     */
    boolean isAuthorCanBeDelete(String id);
}
