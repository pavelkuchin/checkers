package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.dao.GameDao;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public Game getGame(Long gauid) {
        return gameDao.getGame(gauid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game joinGame(Long gauid) {
        //TODO it is bad solution but i have not any other now.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Long uuid = userDao.getUserByLogin(name).getUuid();

        return gameDao.joinGame(gauid, uuid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Game closeGame(Long gauid) {
        Game result = null;
        Game game   = gameDao.getGame(gauid);
        //TODO it is bad solution but i have not any other now.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Long uuid = userDao.getUserByLogin(name).getUuid();


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

        //TODO it is bad solution but i have not any other now.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User you = userDao.getUserByLogin(name);

        game.setGauid(null);

        //TODO constants
        if(game.getType() == null){
            game.setType("offline");
        }
        if(game.getBoard() == null){
            game.setBoard("8x8");
        }
        game.setState("open");

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
    public Game modGame(Long gauid, Game game){
        Game chGame = gameDao.getGame(gauid);

        if(chGame == null)
            return null;

        Collection<SimpleGrantedAuthority> authorities =
                (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        SimpleGrantedAuthority roleAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");

        if(authorities.contains(roleAdmin)){
            if(game.getName() != null){
                chGame.setName(game.getName());
            }
            if(game.getDescription() != null){
                chGame.setDescription(game.getDescription());
            }
        }else if(authorities.contains(roleUser)){
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
    public List<Game> getUserGames(Long uuid) {

        if(uuid == null){
            //TODO it is bad solution but i have not any other now.
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            uuid = userDao.getUserByLogin(name).getUuid();
        }

        return gameDao.getUserGames(uuid);
    }
}
