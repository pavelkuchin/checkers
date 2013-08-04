package com.checkers.server.listeners;

import com.checkers.server.beans.User;
import com.checkers.server.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 *
 *
 * @author Pavel Kuchin
 */

public class AuthenticationListener implements ApplicationListener, ApplicationContextAware {

    static Logger log = Logger.getLogger(AuthenticationListener.class.getName());

    private ApplicationContext appContext;

    AuthenticationListener(){
        log.debug("I created");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent appEvent) {
        if (appEvent instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;

            UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

            String login = userDetails.getUsername();

/*            UserDao userDao = (UserDao)appContext.getBean("userDao");

            User user = userDao.getUserByLogin(login);

            user = userDao.getUser(user.getUuid());

            user.setLastLogin(new Date());

            userDao.modUser(user);

            User nUser = userDao.getUser(user.getUuid());*/

            log.info(login + " - authenticated successfully");
        }
        if (appEvent instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent event = (AuthenticationFailureBadCredentialsEvent)appEvent;

            Object login = event.getAuthentication().getPrincipal();

            log.warn(login + " - bad credentials");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }
}
