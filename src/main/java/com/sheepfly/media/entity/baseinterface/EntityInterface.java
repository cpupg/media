package com.sheepfly.media.entity.baseinterface;

/**
 * 实体类接口。
 *
 * @author wrote-code
 */
public interface EntityInterface {
    /**
     * 主键字段。
     */
    String ID = "id";

    /**
     * 获取主键id。
     *
     * <p>若实体类主键字段为id，则此方法已经由setter实现。</p>
     *
     * @return 主键id。
     */
    String getId();

    /**
     * 设置主键id。
     *
     * <p>若实体类主键字段为id，则此方法已经由setter实现。</p>
     *
     * @param id id。
     */
    void setId(String id);
}
