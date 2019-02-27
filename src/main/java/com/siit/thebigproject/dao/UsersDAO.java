package com.siit.thebigproject.dao;

import com.siit.thebigproject.db.DbException;
import com.siit.thebigproject.domain.User;

import java.sql.SQLException;

public interface UsersDAO extends BaseDAO<User> {

    public boolean updateUserDetails(String username, String email, int calories) throws DbException, SQLException;

    public boolean deleteByUsername(String username) throws DbException, SQLException;


}
