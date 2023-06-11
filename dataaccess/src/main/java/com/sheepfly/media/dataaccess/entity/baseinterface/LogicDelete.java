package com.sheepfly.media.dataaccess.entity.baseinterface;

/**
 * 表示一个实体类可以进行逻辑删除。
 *
 * <p>任何实现此接口的实体类都必须有一个deleteStatus属性，该属性为枚举值，1表示已删除，0表示未删除。</p>
 *
 * <p></p>
 *
 * @author wrote-code
 */
public interface LogicDelete {
    Integer DELETED = 1;
    Integer NOT_DELETED = 0;
    String DELETE_STATUS = "deleteStatus";

    Integer getDeleteStatus();

    void setDeleteStatus(Integer deleteStatus);
}
