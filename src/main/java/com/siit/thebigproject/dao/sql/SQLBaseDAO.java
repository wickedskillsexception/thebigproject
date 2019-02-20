package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.ObjectId;
import org.springframework.stereotype.Repository;
import com.siit.thebigproject.dao.BaseDAO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SQLBaseDAO<T extends ObjectId> implements BaseDAO<T> {
	private Map<Long, T> models = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

	public Collection<T> getAll() throws DbException, SQLException {

		return models.values();
	}

	public T getById(Long id) throws DbException, SQLException {

		return models.get(id);
	}

	public T update(T model) throws DbException, SQLException {
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
