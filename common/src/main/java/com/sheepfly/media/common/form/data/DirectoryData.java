package com.sheepfly.media.common.form.data;

import lombok.Getter;
import lombok.Setter;import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DirectoryData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 父目录代码，从0开始。0表示根目录。根目录只能修改，不能删除。
     */
    @NotNull
    private Long parentCode;

    /**
     * 目录名
     */
    @NotNull
    @Length(min = 1, max = 500)
    private String name;

}
