package com.javaCurse.curse.dao;

import com.javaCurse.curse.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsersList();
    void delete(Long id);

    void register(User user);

    User getUserByCredentials(User user);
}
