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
    Game modGame(Game chGame);
    Game joinGame(Long gauid, Long uuid);
    Game closeGame(Long gauid, Long uuid);
    void delGame(Long gauid);

    List<Game> getGames();
    List<Game> getGamesFiltered(String field, String value);
    List<Game> getUserGames(Long uuid);
}
