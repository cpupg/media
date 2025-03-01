package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.common.exception.BusinessException;
import com.sheepfly.media.common.exception.BusinessRunTimeException;
import com.sheepfly.media.common.exception.ErrorCode;
import com.sheepfly.media.dataaccess.entity.baseinterface.EntityInterface;
import com.sheepfly.media.dataaccess.entity.baseinterface.LogicDelete;
import com.sheepfly.media.service.base.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BaseJpaServiceImpl<T extends EntityInterface, ID, D extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>>
        implements BaseJpaService<T, ID, D> {
    @Autowired
    private D d;
    @Autowired
    private Snowflake snowflake;
    @Autowired
    private EntityManager entityManager;

    @Override
    public T findById(ID id) {
        return d.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        if (t.getId() == null) {
            t.setId(snowflake.nextIdStr());
        }
        if (t instanceof LogicDelete) {
            LogicDelete e = (LogicDelete) t;
            if (e.getDeleteStatus() == null) {
                e.setDeleteStatus(LogicDelete.NOT_DELETED);
            }
        }
        return d.save(t);
    }

    @Override
    public List<T> saveAll(List<T> list) {
        return d.saveAll(list);
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
    public T logicDeleteById(ID id, Class<T> clazz) {
        try {
            T t = d.findById(id).orElse(null);
            if (!(t instanceof LogicDelete)) {
                throw new BusinessRunTimeException(ErrorCode.LOGIC_DELETE_NOT_SUPPORT);
            }
            t.setUpdateTime(new Date());
            LogicDelete e = (LogicDelete) t;
            e.setDeleteStatus(LogicDelete.DELETED);
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

    @Override
    public boolean checkRepeat(T t) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        Example<T> e = Example.of(t, matcher);
        return d.count(e) > 0;
    }

    @Override
    public boolean checkRepeat(Specification<T> specification) {
        return count(specification) > 1;
    }

    @Override
    public List<T> findList(Specification<T> specification) {
        return d.findAll(specification);
    }

    @Override
    public List<T> findList(Example<T> example) {
        return d.findAll(example);
    }

    @Override
    public Optional<T> findOne(Specification<T> specification) {
        return d.findOne(specification);
    }

    @Override
    public Optional<T> findOne(Example<T> example) {
        return d.findOne(example);
    }

    @Override
    public long count(Specification<T> specification) {
        return d.count(specification);
    }

    @Override
    public long count(Example<T> example) {
        return d.count(example);
    }

    @Override
    public T update(T t) {
        t.setUpdateTime(new Date());
        return d.save(t);
    }

    @Override
    public List<T> updateAll(List<T> list) {
        return d.saveAll(list);
    }

    @Override
    public void flush() {
        d.flush();
    }

    @Override
    public void delete(T t) {
        d.delete(t);
    }

    @Override
    public void deleteAll(List<T> list) {
        d.deleteAll(list);
    }

    @Override
    public D getRepository() {
        return d;
    }

    @Override
    public String nextStringId() {
        return snowflake.nextIdStr();
    }

    @Override
    public long nextId() {
        return snowflake.nextId();
    }
}
