package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.ObjectId;
import com.siit.thebigproject.dao.Crud;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class SQLBaseDAO<T extends ObjectId> implements Crud<T> {

    private Map<Long, T> models = new HashMap<Long, T>();

    private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

    @Override
    public void create(T model) {

    }

    @Override
    public Collection<T> readAll() {

        return models.values();
    }

    @Override
    public T getById(Long id) {

        return models.get(id);
    }

    @Override
    public T update(T model) {
        if (model.getId() <= 0) {
            model.setId(ID.getAndIncrement());
        }

        models.put(model.getId(), model);
        return model;
    }

    @Override
    public boolean delete(T model) {
        boolean result = models.containsKey(model.getId());

        if (result)
            models.remove(model.getId());
        return result;
    }
}
