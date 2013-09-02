package com.checkers.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service
public class Context implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static String getAuthLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
