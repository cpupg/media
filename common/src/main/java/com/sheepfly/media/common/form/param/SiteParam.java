package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

import java.io.Serializable;

/**
 * 站点查询表单。
 *
 * @author sheepfly
 */
public class SiteParam extends TablePagination implements Serializable {
    private String siteName;

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
