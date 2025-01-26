package com.sheepfly.media.common.vo;

import com.sheepfly.media.common.http.TablePagination;
import com.sheepfly.media.common.vo.constraintgroup.DeleteConstraint;
import com.sheepfly.media.common.vo.constraintgroup.InsertConstraint;
import com.sheepfly.media.common.vo.constraintgroup.UpdateConstraint;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 收藏视图。
 *
 * @author chen
 */
public class CollectVo extends TablePagination implements Serializable {
    /**
     * 主键id。
     */
    @NotBlank(groups = {UpdateConstraint.class, DeleteConstraint.class}, message = "{entity.collect.collectId.notBlank}")
    private String collectId;

    /**
     * 收藏名称
     */
    @NotNull(groups = InsertConstraint.class, message = "{entity.collect.collectName.notNull}")
    @Length(min = 1, max = 32, groups = {InsertConstraint.class, UpdateConstraint.class}, message = "{entity.collect.collectName.length}")
    private String collectName;

    /**
     * 删除状态
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
