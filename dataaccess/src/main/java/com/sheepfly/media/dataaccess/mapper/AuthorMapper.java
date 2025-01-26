package com.sheepfly.media.dataaccess.mapper;

import com.sheepfly.media.dataaccess.entity.Author;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.form.param.AuthorParam;
import com.sheepfly.media.common.vo.AuthorVo;
import com.sheepfly.media.common.http.TableRequest;
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
    List<Author> queryAuthorList(TableRequest<AuthorParam, AuthorParam, AuthorParam> vo)
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
    List<AuthorVo> queryAuthorVoList(TableRequest<AuthorParam, AuthorParam, AuthorParam> vo)
            throws BusinessException;

    int queryAuthorVoCount(TableRequest<AuthorParam, AuthorParam, AuthorParam> vo);


}
