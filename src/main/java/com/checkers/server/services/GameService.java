package com.checkers.server.services;

import com.checkers.server.beans.Game;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface GameService {
    Game getGame(Long gauid);
    void newGame(Game game);
    List<Game> getGames();
}
