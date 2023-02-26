package com.sheepfly.media.form.filter;

import com.sheepfly.media.vo.common.ProPaginationForm;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
public class SiteFilter extends ProPaginationForm {
    private String siteName;

    @Override
    public String toString() {
        return "SiteFilter{" + "siteName='" + siteName + '\'' + '}';
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
