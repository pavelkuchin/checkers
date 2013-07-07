package com.checkers.server.services;

import com.checkers.server.beans.User;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void newUser(User user) {

        user.setUuid(null);
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public User getUser(Long uuid) {
        return userDao.getUser(uuid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
