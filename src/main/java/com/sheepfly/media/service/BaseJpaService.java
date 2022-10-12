package com.sheepfly.media.service;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa增删改查。
 *
 * @param <T> 实体类型。
 * @param <ID> 主键类型。
 *
 * @author sheepfly
 */
public interface BaseJpaService<T, ID, D extends JpaRepository<T, ID>> {
    /**
     * 根据id查找数据。
     *
     * @param id 主键id。
     *
     * @return 满足条件的数据。
     */
    T findById(ID id);

    /**
     * 保存一个实体对象，需要自行设置主键。
     *
     * @param t 实体对象。
     *
     * @return 保存后的对象。
     */
    T save(T t);

    /**
     * 判断指定id的对象是否存在。
     *
     * @param id id。
     *
     * @return 存在返回true。
     */
    boolean existsById(ID id);

    /**
     * 通过id删除对象。
     *
     * @param id id。
     *
     * @return 被删除的对象。
     */
    void deleteById(ID id);

    /**
     * 删除对象。
     *
     * @param t 要删除的对象。
     */
    void delete(T t);
}
