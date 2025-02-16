package com.sheepfly.media.common.form.sort;

import com.sheepfly.media.common.util.SorterSql;

import java.io.Serializable;

/**
 * 排序。
 *
 * @author chen
 */
public class ResourceSort implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 资源目录
     */
    private String dir;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getOrderBy() {
        return new SorterSql()
                .orderBy("res.update_time", updateTime)
                .orderBy("res.create_time", createTime)
                .orderBy("dir.path", dir)
                .orderBy("res.filename", filename)
                .getSql();
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
