package com.sheepfly.media.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 专辑和资源关联关系。
 *
 * <p>馆连关系只关心专辑和资源，表现到vo里就是只需要关联标识，专辑id和名称，资源id和名称以及
 * 路径，不需要再嵌套vo。</p>
 *
 * @author wrote-code.
 */
@Getter
@Setter
@ToString
public class AlbumResourceVo implements Serializable {
    private String id;
    private String albumId;
    private String albumName;
    private String resourceId;
    private String resourceName;
    private String resourceDir;
}
