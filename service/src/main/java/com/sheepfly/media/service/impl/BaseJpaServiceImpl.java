package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.BusinessRunTimeException;
import com.sheepfly.media.service.BaseJpaService;
import com.sheepfly.media.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class BaseJpaServiceImpl<T extends EntityInterface & LogicDelete, ID, D extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>>
        implements BaseJpaService<T, ID, D> {
    @Autowired
    private D d;
    @Autowired
    private Snowflake snowflake;

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
        return d.existsById(id);
    }

    @Override
    public void deleteById(ID id) {
        d.deleteById(id);
    }

    @Override
    public void safeDeleteById(ID id, ErrorCode errorCode) throws BusinessException {
        if (existsById(id)) {
            deleteById(id);
        } else {
            throw new BusinessException(errorCode);
        }
    }

    @Override
    public T logicDelete(T t) {
        if (t.getUpdateTime() == null) {
            t.setUpdateTime(new Date());
        }
        return d.save(t);
    }

    @Override
    public T logicDeleteById(ID id, Class<T> clazz) {
        try {
            T t = d.findById(id).orElse(null);
            t.setUpdateTime(new Date());
            t.setDeleteStatus(LogicDelete.DELETED);
            return d.save(t);
        } catch (Exception e) {
            throw new BusinessRunTimeException(ErrorCode.LOGIC_DELETE_CREATE_FAIL, e);
        }
    }

    @Override
    public T safeLogicDeleteById(ID id, Class<T> entityType, ErrorCode errorCode) throws BusinessException {
        if (logicExistById(id)) {
            return logicDeleteById(id, entityType);
        } else {
            throw new BusinessException(errorCode);
        }
    }

    @Override
    public boolean logicExistById(ID id) {
        Specification<T> specification = (root, query, builder) -> {
            Predicate pDeleteStatus = builder.equal(root.get(LogicDelete.DELETE_STATUS), LogicDelete.NOT_DELETED);
            Predicate pId = builder.equal(root.get(EntityInterface.ID), id);
            return builder.and(pDeleteStatus, pId);
        };
        long count = d.count(specification);
        return count > 0;
    }
}
