package com.sheepfly.media.form.filter;

import com.sheepfly.media.vo.common.ProPaginationForm;
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
