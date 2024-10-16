package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AlbumParam extends TablePagination implements Serializable {
    /**
     * 资源标识。
     */
    private String resourceId;
    /**
     * 专辑名称。
     */
    private String albumName;
    /**
     * 专辑标识。
     */
    private String albumId;
    /**
     * 是否是selectModal下的组件的表格查询请求。
     */
    private boolean selectModal = false;
}
