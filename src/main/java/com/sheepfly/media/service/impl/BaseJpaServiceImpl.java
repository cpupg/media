package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.config.LogicDeleteEntity;
import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.entity.baseinterface.LogicDelete;
import com.sheepfly.media.exception.BusinessException;
import com.sheepfly.media.service.BaseJpaService;
import com.sheepfly.media.vo.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

@Slf4j
public class BaseJpaServiceImpl<T extends EntityInterface, ID, D extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>>
        implements BaseJpaService<T, ID, D> {
    @Autowired
    private D d;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private LogicDeleteEntity logicDeleteEntity;

    @Override
    public T findById(ID id) {
        Optional<T> byId = d.findById(id);
        return d.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        if (t.getId() == null) {
            t.setId(snowflake.nextIdStr());
        }
        return d.save(t);
    }

    @Override
    public boolean existsById(ID id) {
        // todo 判断是否可以逻辑删除
        return d.existsById(id);
    }

    @Override
    public void deleteById(ID id) {
        // todo 判断是否可以逻辑删除
        d.deleteById(id);
    }

    @Override
    public void deleteById(ID id, ErrorCode errorCode) throws BusinessException {
        // todo 判断是否可以逻辑删除
        if (existsById(id)) {
            deleteById(id);
        } else {
            throw new BusinessException(errorCode);
        }
    }

    @Override
    public void delete(T t) {
        // todo 测试
        if (checkLogicDelete(t.getClass())) {
            logicDeleteById(t.getId());
        } else {
            d.delete(t);
        }
    }

    /**
     * 检查是否可以进行逻辑删除。
     *
     * <p>若一个实体实现了{@link LogicDelete}接口，则可以进行逻辑删除。</p>
     *
     * @param clazz 要检查的实体。
     *
     * @return 可以逻辑删除时返回true。
     */
    private boolean checkLogicDelete(Class<? extends EntityInterface> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> itf : interfaces) {
            if (itf == LogicDelete.class) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void logicDeleteById(String id) {
        // todo 逻辑删除（更新）
        Specification<T> specification = (root, query, builder) -> {
            builder.equal(root.get("DELETE_STATUS"), LogicDelete.DELETED);
            d.save(null);
            return null;
        };
    }

    @Override
    public boolean logicExistById(String id) {
        Specification<T> specification = (root, query, builder) -> {
            Predicate pDeleteStatus = builder.equal(root.get(LogicDelete.DELETE_STATUS), LogicDelete.NOT_DELETED);
            Predicate pId = builder.equal(root.get(EntityInterface.ID), id);
            return builder.and(pDeleteStatus, pId);
        };
        long count = d.count(specification);
        return count > 0;
    }
}
