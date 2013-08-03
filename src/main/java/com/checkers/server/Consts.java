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

    public static final String ME = "me";

    /**
     * Game state:
     *  open - game has been opened. Find black player.
     *  game - game in process
     *  close - game closed (win or dead heat)
     */
    public static final String GAME_STATE_OPEN = "open";
    public static final String GAME_STATE_GAME = "game";
    public static final String GAME_STATE_CLOSE = "close";

    /**
     * Game type:
     *  online - During one session (minutes or hours)
     *  offline - During many sessions (days or months)
     */
    public static final String GAME_TYPE_ONLINE = "online";
    public static final String GAME_TYPE_OFFLINE = "offline";

    /**
     * Board type:
     * 8x8   - Russian checkers
     * 10x10 - Worldwide checkers
     */
    public static final String GAME_BOARD_RUSSIAN = "8x8";
    public static final String GAME_BOARD_WORDWIDE = "10x10";

}
