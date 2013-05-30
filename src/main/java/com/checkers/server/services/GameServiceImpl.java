package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;
import com.checkers.server.dao.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("gameService")
public class GameServiceImpl implements GameService {
    @Autowired
    GameDao gameDao;

    @Override
    public Game getGame(Long gauid) {
        return gameDao.getGame(gauid);
    }

    @Override
    public Game newGame(GameProxy game) {
        return gameDao.newGame(game);
    }

    @Override
    public List<Game> getGames() {
        return gameDao.getGames();
    }

    @Override
    public List<Game> getUserGames(Long uuid) {
        return gameDao.getUserGames(uuid);
    }
}
