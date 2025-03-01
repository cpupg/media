package com.sheepfly.media.service.base;

import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

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
public interface BaseJpaService<T extends EntityInterface, ID, D extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> {
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
     * <p>保存前会检查以下属性，如果没有，会生成默认值：</p>
     * <ul>
     *     <li>id:主键id，使用雪花算法生成。</li>
     *     <li>deleteStatus:删除状态，默认值0。</li>
     * </ul>
     *
     * @param t 实体对象。
     *
     * @return 保存后的对象。
     */
    T save(T t);

    /**
     * 保存全部。
     *
     * @param list。
     *
     * @return 保存的对象。
     */
    List<T> saveAll(List<T> list);

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
     * 将指定id的删除状态改为{@link LogicDelete#DELETED}
     *
     * @param id 主键id。
     * @param clazz 实体类型。
     *
     * @return 已删除的对象。
     */
    T logicDeleteById(ID id, Class<T> clazz);

    /**
     * 将指定id的删除状态改为{@link LogicDelete#DELETED}。
     *
     * <p>删除前会判断对象是否存在，若不存在，则抛出异常。</p>
     *
     * @param id 主键id。
     * @param entityType。
     * @param errorCode 指定对象不存在时的错误码。
     *
     * @return 已删除的对象。
     */
    T safeLogicDeleteById(ID id, Class<T> entityType, ErrorCode errorCode) throws BusinessException;

    /**
     * 判断指定id的对象是否存在且未删除。
     *
     * @param id 主键id。
     *
     * @return 存在时返回true。
     */
    boolean logicExistById(ID id);

    /**
     * 检查要保存的实体是否重复，如果重复，返回true，否则返回false。
     *
     * @param t 去重参数。
     *
     * @return 是否重复。
     */
    boolean checkRepeat(T t);

    boolean checkRepeat(Specification<T> specification);

    List<T> findList(Specification<T> specification);

    List<T> findList(Example<T> example);

    Optional<T> findOne(Specification<T> specification);

    Optional<T> findOne(Example<T> example);

    long count(Specification<T> specification);

    long count(Example<T> example);

    T update(T t);

    List<T> updateAll(List<T> list);

    /**
     * 刷新实体管理器上下文。
     *
     * <p>不是所有的save场景都需要刷新，因此不需要添加saveAndFlush方法。</p>
     */
    void flush();

    void delete(T t);

    void deleteAll(List<T> list);

    /**
     * 获取仓库对象。
     *
     * @return 仓库对象。
     */
    D getRepository();

    /**
     * 使用雪花算法获取id。
     *
     * @return id
     */
    String nextStringId();

    /**
     * 使用雪花算法获取id。
     *
     * @return id
     */
    long nextId();

}
