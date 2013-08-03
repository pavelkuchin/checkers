package com.checkers.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Context {
    public static String getAuthLogin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
    }

}
