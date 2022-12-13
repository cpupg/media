package com.sheepfly.media.service;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.vo.common.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa增删改查。
 *
 * <p>接口继承了两个父接口，一个是jpa的基本增删改查，一个是调用criteria的增删改查。</p>
 *
 * @param <T> 实体类型。
 * @param <ID> 主键类型。
 * @param <D> 实体类对应的repository。
 *
 * @author sheepfly
 */
public interface BaseJpaService<T extends EntityInterface, ID, D extends JpaRepository<T, ID>> {
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
     * <p>保存前会先检查是否设置了主键id，若没有设置主键id，则自动生成主键id。</p>
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
     */
    void deleteById(ID id);

    /**
     * 通过id删除对象。
     *
     * <p>删除前会先调用{@link #existsById(Object)}进行判断，如果不存在，则会抛出
     * 异常，异常类型为ErrorCode。</p>
     *
     * @param id 主键id。
     * @param errorCode 错误码。
     */
    void deleteById(ID id, ErrorCode errorCode) throws BusinessException;

    /**
     * 删除对象。
     *
     * @param t 要删除的对象。
     */
    void delete(T t);
}
