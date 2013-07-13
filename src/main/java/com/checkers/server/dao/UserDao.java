package com.checkers.server.dao;

import com.checkers.server.beans.User;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserDao {
    void newUser(User user);

    User getUser(Long uuid);

    void delUser(Long uuid);

    User modUser(User user);

    User getUserByLogin(String login);

    List<User> getUsers();
}
