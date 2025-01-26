package com.sheepfly.media.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源目录
 */
@Entity
@Table(schema = "MEDIA", name = "DIRECTORY",
        indexes = {@Index(
                name = "media.media_directory_1",
                columnList = "DIR_CODE",
                unique = true
        ), @Index(
                name = "media.media_directory_2",
                columnList = "CODE_LIST"
        )})
public class Directory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @Column(name = "ID", nullable = false, length = 19)
    private String id;

    /**
     * 目录代码，每个目录都有一个全局唯一的目录代码。
     *
     * <p>根目录和盘符是两种特殊目录。根目录只有一个/，目录代码是0，盘符可以有多个，从0依次
     * 递减，因此盘符的目录代码是负数。</p>
     */
    @Column(name = "DIR_CODE", nullable = false)
    private Long dirCode;

    /**
     * 父目录代码，从0开始。0表示根目录。根目录只能修改，不能删除。
     */
    @Column(name = "PARENT_CODE", nullable = false)
    private Long parentCode;

    /**
     * 目录名
     */
    @Column(name = "NAME", nullable = false, length = 500)
    private String name;

    /**
     * 全路径。
     *
     * <p>分隔符是斜杠/，不区分平台，且必须以一个斜杠结尾。此外，目录区分大小写。</p>
     */
    @Column(name = "PATH", nullable = false, length = 1000)
    private String path;

    /**
     * 全路径对应的目录代码清单。
     *
     * <p>假如现在有一个目录e有五个层级，全路径是 /a/b/c/d/e，对应的目录代码是/1/2/3/4/5，
     * 则code_list的代码清单是0.1.2.3.4.5。如果目录带了盘符，则目录代码是-1.0.1.2.3.4.5。
     * 注意，为了防止目录层级太多导致长度超过100，这里的目录代码使用的是16进制。</p>
     */
    @Column(name = "CODE_LIST", nullable = false, length = 100)
    private String codeList;

    /**
     * 目录层级。
     *
     * <p>目录代码清单按小数点分隔后得到的数字个数就是目录层级。盘符和根目录的层级是0，</p>
     */
    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    /**
     * 删除状态0未删除1删除
     */
    @Column(name = "DELETE_STATUS", nullable = false)
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 删除时间
     */
    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Integer getDeleteStatus() {
        return this.deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
