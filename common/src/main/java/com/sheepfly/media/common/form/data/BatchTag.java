package com.sheepfly.media.common.form.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BatchTag implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tags;
    private String resourceIds;
}
