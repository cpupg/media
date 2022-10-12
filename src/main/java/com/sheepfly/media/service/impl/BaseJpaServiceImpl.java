package com.sheepfly.media.service.impl;

import com.sheepfly.media.service.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class BaseJpaServiceImpl<T, ID, D extends JpaRepository<T, ID>> implements BaseJpaService<T, ID, D> {
    @Autowired
    private D d;

    @Override
    public T findById(ID id) {
        Optional<T> byId = d.findById(id);
        return d.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
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
