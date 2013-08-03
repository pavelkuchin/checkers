package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.exceptions.LogicException;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface GameService {
    Game getGame(Long gauid) throws LogicException;
    Game joinGame(Long gauid) throws LogicException;
    Game closeGame(Long gauid) throws LogicException;
    Game newGame(Game game);
    Game modGame(Long gauid, Game game) throws LogicException;
    List<Game> getGames();
    List<Game> getGamesFiltered(String field, String value);
    List<Game> getUserGames(Long uuid) throws LogicException;
}
