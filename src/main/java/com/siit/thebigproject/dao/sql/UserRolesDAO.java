package com.siit.thebigproject.dao.sql;

import com.siit.thebigproject.dao.BaseDAO;
import com.siit.thebigproject.domain.UserRole;

public interface UserRolesDAO extends BaseDAO<UserRole> {

    public boolean deleteByUserId(long userId);
}
