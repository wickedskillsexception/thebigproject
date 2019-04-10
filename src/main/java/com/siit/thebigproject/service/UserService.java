package com.siit.thebigproject.service;

import com.siit.thebigproject.dao.sql.SQLUsersDAO;
import com.siit.thebigproject.domain.User;
import com.siit.thebigproject.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SQLUsersDAO usersDAO;

    public Collection<User> listAll() {
        return usersDAO.getAll();
    }


    public boolean delete(Long id) {
        LOGGER.debug("Deleting user with id: " + id);
        User user = null;
        user = usersDAO.getById(id);

        return usersDAO.delete(user);
    }

    public User get(Long id) {
        LOGGER.debug("Getting user with id: " + id);
        return usersDAO.getById(id);
    }

    public User getByEmail(String email){
        return usersDAO.getByEmail(email);
    }

    public void save(User user) throws ValidationException {
        LOGGER.debug("Saving: " + user);
        List<String> errors = new LinkedList<>();
        if (StringUtils.isEmpty(user.getFullName())) {
            errors.add("Full Name is Empty");
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            errors.add("Username is Empty");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            errors.add("User password is Empty");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.add("User email is Empty");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
        usersDAO.update(user);
    }

    public SQLUsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(SQLUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }
}
