package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.ObjectId;

import java.sql.SQLException;
import java.util.Collection;


public interface BaseDAO< T extends  ObjectId> {

    void add(T object) throws DbException, SQLException;

    Collection<T> getAll() throws DbException, SQLException;

    T getById(Long id) throws DbException, SQLException;

    T update(T model) throws DbException, SQLException;

    boolean delete(T model) throws DbException, SQLException;
}
