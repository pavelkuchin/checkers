package com.checkers.server.events;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;

/**
 *
 *
 *
 * @author Pavel_Kuchin
 */
public class GameEvent extends MyEvent{
    private Game game;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public GameEvent(Object source, Game game) {
        super(source, EventName.GAME);
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
