package com.siit.thebigproject.db;

import com.siit.thebigproject.base.ObjectId;

import java.util.Collection;


public interface Crud < T extends  ObjectId> {

    void create(T model);

    Collection<T> readAll();

    T getById(Long id);

    T update(T model);

    boolean delete(T model);


}
