package com.checkers.server.services;

import com.checkers.server.Consts;
import com.checkers.server.Context;
import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.dao.GameDao;
import com.checkers.server.dao.UserDao;
import com.checkers.server.exceptions.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    private GameDao gameDao;

    @Autowired
    private UserDao userDao;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game getGame(Long gauid) throws LogicException {
        return gameDao.getGame(gauid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game joinGame(Long gauid) throws LogicException {
        Long uuid = userDao.getUserByLogin(Context.getAuthLogin()).getUuid();

        return gameDao.joinGame(gauid, uuid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game closeGame(Long gauid) throws LogicException {
        Game result = null;
        Game game   = gameDao.getGame(gauid);

        Long uuid = userDao.getUserByLogin(Context.getAuthLogin()).getUuid();

        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if(authorities.contains(Consts.ROLE_USER)){
            if(!(game.getBlackUuid() == uuid || game.getWhiteUuid() == uuid)){
                throw new LogicException(1L, "You are not involved in game.");
            }
        }

        if(game.getState().equals("open")){
            gameDao.delGame(gauid);
            result = null;
        } else if(game.getState().equals("game")){
            result = gameDao.closeGame(gauid, uuid);
        }

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game newGame(Game game) {

        User you = userDao.getUserByLogin(Context.getAuthLogin());

        game.setGauid(null);

        if(game.getType() == null){
            game.setType(Consts.GAME_TYPE_OFFLINE);
        }
        if(game.getBoard() == null){
            game.setBoard(Consts.GAME_BOARD_RUSSIAN);
        }
        game.setState(Consts.GAME_STATE_OPEN);

        game.setResolution("");

        game.setWhite(you);
        game.setWhiteUuid(you.getUuid());

        game.setBlack(null);
        game.setBlackUuid(null);

        game.setCreated(new Date());
        game.setModified(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);
            return game;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game modGame(Long gauid, Game game) throws LogicException {
        Game chGame = gameDao.getGame(gauid);

        if(chGame == null)
            return null;

        User user = userDao.getUserByLogin(Context.getAuthLogin());

        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if(authorities.contains(Consts.ROLE_ADMIN)){
            if(game.getName() != null){
                chGame.setName(game.getName());
            }
            if(game.getDescription() != null){
                chGame.setDescription(game.getDescription());
            }
        }else if(authorities.contains(Consts.ROLE_USER)){
            if(!(game.getBlackUuid() == user.getUuid() || game.getWhiteUuid() == user.getUuid())){
                throw new LogicException(1L, "You are not involved in game.");
            }

            if(game.getDescription() != null){
                chGame.setDescription(game.getDescription());
            }
        }

        chGame.setModified(new Date());

        gameDao.modGame(chGame);

        return gameDao.getGame(gauid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Game> getGames() {
        return gameDao.getGames();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Game> getGamesFiltered(String field, String value) {
        return gameDao.getGamesFiltered(field, value);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Game> getUserGames(Long uuid) throws LogicException {

        if(uuid == null){
            uuid = userDao.getUserByLogin(Context.getAuthLogin()).getUuid();
        }

        return gameDao.getUserGames(uuid);
    }
}
