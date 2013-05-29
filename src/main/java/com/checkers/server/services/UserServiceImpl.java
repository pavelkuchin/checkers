package com.checkers.server.services;

import com.checkers.server.beans.User;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void newUser(User user) {
        userDao.newUser(user);
    }

    @Override
    public User getUser(Long uuid) {
        return userDao.getUser(uuid);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
