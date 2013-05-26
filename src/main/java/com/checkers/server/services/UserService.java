package com.checkers.server.services;

import com.checkers.server.beans.User;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface UserService {
    void newUser(User user);

    User getUser(Long uuid);

    List<User> getUsers();
}
