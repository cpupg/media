package com.sheepfly.media.dataaccess.entity;

import lombok.Data;

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
@Data
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
     * <p>分隔符是斜杠/，不区分平台，且必须以一个斜杠结尾。</p>
     */
    @Column(name = "PATH", nullable = false, length = 1000)
    private String path;

    /**
     * 全路径对应的目录代码清单。
     *
     * <p>假如现在有一个目录e有五个层级，全路径是 /a/b/c/d/e，对应的目录吗分别是1,2,3,4,5。若
     * a是根目录，则a的目录代码是0，否则不能为0。此时，e的目录代码清单是1.2.3.4.5。若a是根目录，
     * 则目录代码清单是0.2.3.4.5</p>
     */
    @Column(name = "CODE_LIST", nullable = false, length = 100)
    private String codeList;

    /**
     * 目录层级。
     *
     * <p>目录代码清单按小数点分隔后得到的数字个数就是目录层级。</p>
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

}
