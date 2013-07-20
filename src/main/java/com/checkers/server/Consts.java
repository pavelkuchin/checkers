package com.checkers.server;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 *
 * @author Pavel Kuchin
 */
public class Consts {
    public static final SimpleGrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
    public static final SimpleGrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");

    public static final String ME = Consts.ME;

}
