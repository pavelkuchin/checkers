package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Repository("gameDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GameDaoImpl implements GameDao {

    static Logger log = Logger.getLogger(GameDaoImpl.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Game getGame(Long gauid) {
        Game game = null;

        try{
            game = em.find(Game.class, gauid);
        } catch(Exception e){
            //Catch any exception
            log.error("getGame: " + e.getMessage(), e);
        }

        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void newGame(Game game) {
        try{
            if(game.getWhite() != null){
                game.setWhiteUuid(game.getWhite().getUuid());
            } else{
                if(game.getWhiteUuid() != null){
                    game.setWhite(em.getReference(User.class, game.getWhiteUuid()));
                } else{
                    throw new Exception("Attempt to create a game without a pointer to the white player");
                }
            }

            if(game.getBlack() != null){
                game.setBlackUuid(game.getBlack().getUuid());
            } else{
                if(game.getBlackUuid() != null){
                    game.setBlack(em.getReference(User.class, game.getBlackUuid()));
                }
            }

            em.persist(game);
        } catch(Exception e){
            //Catch any exception
            log.error("newGame: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Game joinGame(Long gauid, Long uuid) {

        Game game = em.find(Game.class, gauid);

        // TODO constants
        /**
         * Game state:
         *  open - game has been opened. Find black player.
         *  game - game in process
         *  close - game closed (win or dead heat)
         */
        if(game.getState().equals("open") && game.getWhiteUuid() != uuid){
            game.setType("game");
            game.setBlackUuid(uuid);
            game.setBlack(em.find(User.class, uuid));

            em.merge(game);
        }

        return game;
    }


    @Override
    public List<Game> getGames() {
        List<Game> games = null;

        try{
            games = em.createQuery("SELECT g FROM Game g").getResultList();
        } catch(Exception e){
            //Catch any exception
            log.error("getGames: " + e.getMessage(), e);
        }

            return games;
    }

    @Override
    public List<Game> getUserGames(Long uuid) {
        List<Game> games = null;

        try{
            games = em.createQuery("SELECT g FROM Game g WHERE g.white.uuid = :uuid OR g.black.uuid = :uuid")
                    .setParameter("uuid", uuid)
                    .getResultList();
        } catch(Exception e){
            //Catch any exception
            log.error("getUserGames: " + e.getMessage(), e);

        }

            return games;
    }
}
