package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.filter.AuthorFilter;
import com.sheepfly.media.dataaccess.vo.AuthorVo;
import com.sheepfly.media.common.http.ProComponentsRequestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 创作人员 Mapper 接口
 * </p>
 *
 * @author sheepfly
 * @since 2022-02-07
 */
@Mapper
public interface AuthorMapper {
    /**
     * 查询作者清单。
     *
     * @param vo 参数。
     *
     * @return 查询结果。
     *
     * @throws BusinessException 业务异常。
     */
    List<Author> queryAuthorList(ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo)
            throws BusinessException;

    /**
     * 查询作者清单。
     *
     * @param vo 参数。
     *
     * @return 查询结果。
     *
     * @throws BusinessException 业务异常。
     */
    List<AuthorVo> queryAuthorVoList(ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo)
            throws BusinessException;

    int queryAuthorVoCount(ProComponentsRequestVo<AuthorFilter, AuthorFilter, AuthorFilter> vo);


}
