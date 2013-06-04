package com.checkers.server.dao;

import com.checkers.server.beans.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Tests for {@link UserDaoImpl}
 *
 * @author Pavel Kuchin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-context.xml")
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void newUser(){
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiyancitipate");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy@gmail.com");

        user.setEnabled(true);

        user.setRole("USER_ROLE");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        Assert.assertNotNull("User's object doesn't persist.", user.getUuid());
    }

    @Test
    public void getUser(){
        //User creation.
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy@gmail.com");

        user.setEnabled(true);

        user.setRole("USER_ROLE");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        User obtainedUser = userDao.getUser(user.getUuid());

        Assert.assertNotNull("Obtained user doesn't exist.", obtainedUser);
        Assert.assertNotNull("Obtained user uuid is null.", obtainedUser.getUuid());
        Assert.assertEquals("Obtained user uuid is incorrect.", user.getUuid(), obtainedUser.getUuid());
    }

    public void getUsers(){
        List<User> users = userDao.getUsers();

        Assert.assertNotNull("Users list is null.", users);
        Assert.assertNotEquals("Not all records are returned (or more than you need).", 0, users.size());
    }


}
