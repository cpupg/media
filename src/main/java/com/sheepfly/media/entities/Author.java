package com.sheepfly.media.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sheepfly
 * @since 2021-11-06
 */
@TableName("AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;

    @TableField("NAME")
    private String name;

    @TableField("CREATE_TIME")
    private String createTime;

    @TableField("UPDATE_TIME")
    private String updateTime;

    @TableField("SOURCE")
    private String source;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Author{" +
        "id=" + id +
        ", name=" + name +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", source=" + source +
        "}";
    }
}
