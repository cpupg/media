package com.sheepfly.media.common.form.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TagData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int currentPage;
    private int pageSize;
    /**
     * 是否是评分标签。
     *
     * <p>评分标签只能是0-10的数字且只能有一个。</p>
     */
    private boolean rate;
    /**
     * 是否是收藏标签，name=收藏，只能有一个。
     */
    private boolean favourite;
}
