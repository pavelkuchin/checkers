package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;

import java.util.List;

/**
 *
 *
 * @author Pavel_Kuchin
 */
public interface GameService {
    Game getGame(Long gauid);
    Game newGame(GameProxy game);
    List<Game> getGames();
    List<Game> getUserGames(Long uuid);
}
