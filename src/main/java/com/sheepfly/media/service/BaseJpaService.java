package com.sheepfly.media.service;

import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.entity.baseinterface.LogicDelete;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.vo.common.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
public interface BaseJpaService<T extends EntityInterface & LogicDelete, ID, D extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> {
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
    void safeDeleteById(ID id, ErrorCode errorCode) throws BusinessException;

    /**
     * 逻辑删除一个对象。
     *
     * <p>为防止副作用，建议只设置主键id和删除状态两个字段。</p>
     *
     * @param t 要删除的对象。
     *
     * @return 已删除的对象。
     */
    T logicDelete(T t);

    /**
     * 将指定id的删除状态改为{@link LogicDelete#DELETED}
     *
     * @param id 主键id。
     * @param clazz 实体类型。
     *
     * @return 已删除的对象。
     */
    T logicDeleteById(String id, Class<T> clazz);

    /**
     * 将指定id的删除状态改为{@link LogicDelete#DELETED}。
     *
     * <p>删除前会判断对象是否存在，若不存在，则抛出异常。</p>
     *
     * @param id 主键id。
     * @param entityType。
     * @param errorCode 指定独享不存在时的错误码。
     *
     * @return 已删除的对象。
     */
    T safeLogicDeleteById(String id, Class<T> entityType, ErrorCode errorCode) throws BusinessException;

    /**
     * 判断指定id的对象是否存在且未删除。
     *
     * @param id 主键id。
     *
     * @return 存在时返回true。
     */
    boolean logicExistById(String id);
}
