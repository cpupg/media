package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;
import lombok.Data;

import java.io.Serializable;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
@Data
public class SiteParam extends TablePagination implements Serializable {
    private String siteName;
}
