package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface GameDao {
    Game getGame(Long gauid);
    void newGame(Game game);
    Game newGame(GameProxy gameProxy);

    List<Game> getGames();
    List<Game> getUserGames(Long uuid);
}
