package com.checkers.server.beans.proxy;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.beans.User;

/**
 *
 * @author Pavel Kuchin
 */
public class StepProxy extends Step{
    private Long gauid;
    private Long uuid;

    public void setGame(Long gauid) {
        this.gauid = gauid;
    }

    public void setUser(Long uuid) {
        this.uuid = uuid;
    }

    public Long getGauid() {
        return gauid;
    }

    public Long getUuid() {
        return uuid;
    }
}
