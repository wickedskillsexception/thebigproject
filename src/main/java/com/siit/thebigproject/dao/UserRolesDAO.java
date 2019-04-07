package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.UserRole;

public interface UserRolesDAO extends BaseDAO<UserRole> {

    public boolean deleteByUserId(long userId);
}
