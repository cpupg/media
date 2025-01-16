package com.sheepfly.media.common.form.data;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DirRepoData implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @Length(min = 1, max = 32, message = "{entity.dirRepo.name.length}")
    private String name;
    @NotNull
    @Length(min = 1, max = 500, message = "{entity.dirRepo.path.length}")
    private String path;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
