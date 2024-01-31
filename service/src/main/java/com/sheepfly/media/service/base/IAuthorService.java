package com.sheepfly.media.service.base;

import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.param.AuthorParam;
import com.sheepfly.media.dataaccess.repository.AuthorRepository;
import com.sheepfly.media.dataaccess.vo.AuthorVo;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;

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
    TableResponse<AuthorVo> queryForAuthorList(TableRequest<AuthorParam, AuthorParam, AuthorParam> vo)
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
