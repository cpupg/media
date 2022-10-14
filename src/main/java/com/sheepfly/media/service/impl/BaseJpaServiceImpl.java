package com.sheepfly.media.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.sheepfly.media.entity.baseinterface.EntityInterface;
import com.sheepfly.media.service.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class BaseJpaServiceImpl<T extends EntityInterface, ID, D extends JpaRepository<T, ID>>
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
    public void delete(T t) {
        d.delete(t);
    }
}
