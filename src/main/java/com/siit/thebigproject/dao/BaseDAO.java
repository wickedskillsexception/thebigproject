package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.ObjectId;

import java.util.Collection;


public interface BaseDAO< T extends  ObjectId> {

    Collection<T> getAll();

    T getById(Long id);

    T update(T model);

    boolean delete(T model);
}
