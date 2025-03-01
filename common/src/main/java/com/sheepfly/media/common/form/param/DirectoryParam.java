package com.sheepfly.media.common.form.param;

import com.sheepfly.media.common.http.TablePagination;

import java.io.Serializable;

/**
 * 目录过滤器。
 *
 * @author wrote-code
 */
public class DirectoryParam extends TablePagination implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 目录代码，每个目录都有一个全局唯一的目录代码。
     */
    private Long dirCode;
    /**
     * 父目录代码，从0开始。0表示根目录。根目录只能修改，不能删除。
     */
    private Long parentCode;
    /**
     * 目录名
     */
    private String name;
    /**
     * 全路径对应的目录代码清单。
     *
     * <p>假如现在有一个目录e有五个层级，全路径是 /a/b/c/d/e，对应的目录吗分别是1,2,3,4,5。若
     * a是根目录，则a的目录代码是0，否则不能为0。此时，e的目录代码清单是1.2.3.4.5。若a是根目录，
     * 则目录代码清单是0.2.3.4.5</p>
     */
    private String codeList;
    /**
     * 目录层级。
     *
     * <p>目录代码清单按小数点分隔后得到的数字个数就是目录层级。</p>
     */
    private Integer level;

    public Long getDirCode() {
        return this.dirCode;
    }

    public void setDirCode(Long dirCode) {
        this.dirCode = dirCode;
    }

    public Long getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeList() {
        return this.codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
