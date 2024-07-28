package com.sheepfly.media.service.base;

import com.sheepfly.media.common.form.filter.AlbumFilter;
import com.sheepfly.media.common.form.param.AlbumParam;
import com.sheepfly.media.common.form.sort.AlbumSort;
import com.sheepfly.media.common.http.TableRequest;
import com.sheepfly.media.common.http.TableResponse;
import com.sheepfly.media.dataaccess.entity.AlbumResource;
import com.sheepfly.media.dataaccess.repository.AlbumResourceRepository;
import com.sheepfly.media.common.vo.AlbumResourceVo;

/**
 * 专辑资源服务。
 *
 * <p>专辑相关操作放到AlbumService或按照业务加在其他service里，此文件不再新增方法。</p>
 *
 * @author chen
 */
public interface AlbumResourceService extends BaseJpaService<AlbumResource, String, AlbumResourceRepository> {
    /**
     * 查询包含指定资源的专辑。
     *
     * @param tableRequest 查询参数。
     *
     * @return 查询结果。
     */
    TableResponse<AlbumResourceVo> queryAlbumResourceList(
            TableRequest<AlbumFilter, AlbumParam, AlbumSort> tableRequest);
}
