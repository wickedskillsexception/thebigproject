package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.domain.ObjectId;
import com.siit.thebigproject.dao.BaseDAO;
import org.h2.message.DbException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SQLBaseDAO<T extends ObjectId> implements BaseDAO<T> {

    private Map<Long, T> models = new HashMap<Long, T>();

    private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

    public Collection<T> getAll() {

        return models.values();
    }

    public T getById(Long id) {

        return models.get(id);
    }

    public T update(T model) {
        if (model.getId() <= 0) {
            model.setId(ID.getAndIncrement());
        }

        models.put(model.getId(), model);
        return model;
    }

    public boolean delete(T model) {
        boolean result = models.containsKey(model.getId());

        if (result)
            models.remove(model.getId());
        return result;
    }
}
