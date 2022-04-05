package com.sheepfly.media.form.querylist;

import com.sheepfly.media.vo.common.ProPaginationForm;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
public class SiteForm extends ProPaginationForm {
    private String siteName;

    @Override
    public String toString() {
        return "SiteForm{" + "siteName='" + siteName + '\'' + '}';
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
