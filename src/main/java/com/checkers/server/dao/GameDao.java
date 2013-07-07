package com.checkers.server.dao;

import com.checkers.server.beans.Game;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface GameDao {
    Game getGame(Long gauid);
    void newGame(Game game);

    List<Game> getGames();
    List<Game> getUserGames(Long uuid);
}
