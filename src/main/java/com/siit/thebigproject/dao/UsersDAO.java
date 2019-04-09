package com.siit.thebigproject.dao;

import com.siit.thebigproject.domain.User;

public interface UsersDAO extends BaseDAO<User> {
    public User getByEmail(String email);

}
