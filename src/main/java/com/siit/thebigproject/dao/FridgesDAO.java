package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.Fridge;

public interface FridgesDAO extends BaseDAO<Fridge> {

    public boolean deleteByUserId(long id);

}
