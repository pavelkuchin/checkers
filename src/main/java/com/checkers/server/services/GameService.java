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
    Game closeGame(Long gauid);
    Game newGame(Game game);
    Game modGame(Long gauid, Game game);
    List<Game> getGames();
    List<Game> getGamesFiltered(String field, String value);
    List<Game> getUserGames(Long uuid);
}
