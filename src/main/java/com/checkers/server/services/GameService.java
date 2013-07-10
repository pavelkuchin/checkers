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
    Game joinGame(Long gauid);
    Game newGame(Game game);
    List<Game> getGames();
    List<Game> getUserGames(Long uuid);
}
