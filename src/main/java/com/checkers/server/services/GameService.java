package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.ListenObjects;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface GameService {
    Game getGame(Long gauid) throws ApplicationException;
    ListenObjects listenGameAsync(String username, Long gauid, Long suid, String gameState)
            throws ApplicationException, InterruptedException;
    Game joinGame(Long gauid) throws ApplicationException;
    Game closeGame(Long gauid) throws ApplicationException;
    Game newGame(Game game) throws ApplicationException;
    Game modGame(Long gauid, Game game) throws ApplicationException;
    List<Game> getGames();
    List<Game> getGamesFiltered(String field, String value);
    List<Game> getUserGames(Long uuid) throws ApplicationException;
}
