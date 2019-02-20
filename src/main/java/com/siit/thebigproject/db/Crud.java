package com.siit.thebigproject.db;

import com.siit.thebigproject.base.ObjectId;

import java.util.Collection;


public interface Crud<T> {

    void create(T object);

    Collection<T> readAll();

    T getById(Long id);

    T update(T object);

    boolean delete(T object);


}
