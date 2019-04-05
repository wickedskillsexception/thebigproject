package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.ObjectId;
import org.h2.message.DbException;

import java.util.Collection;


public interface BaseDAO< T extends  ObjectId> {

    Collection<T> getAll();

    T getById(Long id);

    T update(T model);

    boolean delete(T model);
}
