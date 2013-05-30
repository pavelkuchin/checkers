package com.checkers.server.beans.proxy;

import com.checkers.server.beans.Game;

/**
 *
 * TODO MappingJacksonHttpMessageConverter implementation would be better than Proxies
 * @author Pavel Kuchin
 */
public class GameProxy extends Game {
    protected Long whiteUuid;
    protected Long blackUuid;

    public void setWhite(Long white) {
        this.whiteUuid = white;
    }

    public Long getWhiteUuid() {
        return this.whiteUuid;
    }

    public void setBlack(Long black) {
        this.blackUuid = black;
    }

    public Long getBlackUuid() {
        return this.blackUuid;
    }

}
