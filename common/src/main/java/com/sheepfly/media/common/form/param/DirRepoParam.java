package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class DirRepoParam extends TablePagination implements Serializable {
    private static final long serialVersionUID = 1L;
}
