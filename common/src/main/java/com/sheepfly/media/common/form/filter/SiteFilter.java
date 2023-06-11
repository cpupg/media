package com.sheepfly.media.common.form.filter;

import com.sheepfly.media.common.http.ProPaginationForm;
import lombok.Data;

import java.io.Serializable;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
@Data
public class SiteFilter extends ProPaginationForm implements Serializable {
    private String siteName;
}
