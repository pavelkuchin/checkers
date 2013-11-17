package com.checkers.server.dao;

import com.checkers.server.beans.User;
import com.checkers.server.exceptions.ApplicationException;
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
    public void newUser() throws ApplicationException {
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy1");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy1@gmail.com");

        user.setEnabled(true);

        user.setRole("ROLE_USER");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        Assert.assertNotNull("User's object doesn't persist.", user.getUuid());
    }

    @Test
    public void modUser() throws ApplicationException {
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy2");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy2@gmail.com");

        user.setEnabled(true);

        user.setRole("ROLE_USER");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        user.setFirstName("Pasha");

        userDao.modUser(user);

        User result = userDao.getUser(user.getUuid());

        Assert.assertEquals("User's object doesn't change.", "Pasha", result.getFirstName());
    }

    @Test(expected = ApplicationException.class)
    public void delUser() throws ApplicationException {
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy3");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy3@gmail.com");

        user.setEnabled(true);

        user.setRole("ROLE_USER");

        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());

        userDao.newUser(user);

        userDao.delUser(user.getUuid());

        userDao.getUser(user.getUuid());
    }

    @Test
    public void getUser() throws ApplicationException {
        //User creation.
        User user = new User();

        user.setUuid(null);

        user.setLogin("Konstantin.Konstantinopolskiy4");
        user.setFirstName("Konstantin");
        user.setLastName("Konstantinopolskiy");
        user.setPassword("jgu*Gg(m574mLU");

        user.setEmail("Konstantin.Konstantinopolskiy4@gmail.com");

        user.setEnabled(true);

        user.setRole("ROLE_USER");

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
