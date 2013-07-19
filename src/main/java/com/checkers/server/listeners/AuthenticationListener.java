package com.checkers.server.listeners;

import com.checkers.server.beans.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 *
 *
 * @author Pavel Kuchin
 */

@Component
public class AuthenticationListener implements ApplicationListener {

    static Logger log = Logger.getLogger(AuthenticationListener.class.getName());

    @Autowired
    com.checkers.server.dao.UserDao userDao;

    @Override
    public void onApplicationEvent(ApplicationEvent appEvent) {
        if (appEvent instanceof AuthenticationSuccessEvent)
        {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;

            UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

            String login = userDetails.getUsername();

            User user = userDao.getUserByLogin(login);

            user.setLastLogin(new Date());

            userDao.modUser(user);

            log.info(login + " - authenticated successfully");
        }
    }
}
