package com.sheepfly.media.common.form.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AlbumParam implements Serializable {
    /**
     * 资源标识。
     */
    private String resourceId;
    /**
     * 专辑名称。
     */
    private String albumName;
}
