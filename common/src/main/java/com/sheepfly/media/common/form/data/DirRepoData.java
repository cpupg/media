package com.sheepfly.media.common.form.data;

import lombok.Getter;
import lombok.Setter;import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DirRepoData implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @Length(min = 1, max = 32, message = "{entity.dirRepo.name.length}")
    private String name;
    @NotNull
    @Length(min = 1, max = 500, message = "{entity.dirRepo.path.length}")
    private String path;
}
