package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.dao.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game getGame(Long gauid) {
        return gameDao.getGame(gauid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game newGame(Game game) {

        game.setGauid(null);

        game.setCreated(new Date());
        game.setModified(new Date());
        game.setLastLogin(new Date());

        gameDao.newGame(game);
            return game;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Game> getGames() {
        return gameDao.getGames();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Game> getUserGames(Long uuid) {
        return gameDao.getUserGames(uuid);
    }
}
