package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Getter;
import lombok.Setter;import lombok.ToString;

import java.io.Serializable;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
@Getter
@Setter
@ToString
public class SiteParam extends TablePagination implements Serializable {
    private String siteName;
}
