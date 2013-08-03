package com.checkers.server.dao;

import com.checkers.server.Consts;
import com.checkers.server.beans.Game;
import com.checkers.server.beans.User;
import com.checkers.server.exceptions.LogicException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public Game getGame(Long gauid) throws LogicException {
        Game game = null;

        try{
            game = em.find(Game.class, gauid);
            if(game == null){
                throw new LogicException(4L, "Game not found");
            }
        } catch (LogicException le){
            throw le;
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
    public Game modGame(Game game){
        try{
            em.merge(game);
        }catch (Exception e){
            //Catch any exception
            log.error("modGame" + e.getMessage(), e);
        }

        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Game joinGame(Long gauid, Long uuid) throws LogicException {
        Game game = null;

        try{
            game = this.getGame(gauid);

            if(game.getState().equals(Consts.GAME_STATE_OPEN) && game.getWhiteUuid() != uuid){
                game.setState(Consts.GAME_STATE_GAME);
                game.setBlackUuid(uuid);
                game.setBlack(em.find(User.class, uuid));

                em.merge(game);
            } else{
                if(game.getState().contains(Consts.GAME_STATE_CLOSE) || game.getState().equals(Consts.GAME_STATE_GAME)){
                    throw new LogicException(8L, "Incorrect game state.");
                }
                if(game.getWhiteUuid() == uuid){
                    throw new LogicException(9L, "It is your game.");
                }
            }
        } catch (LogicException le){
            throw le;
        } catch (Exception e){
            //Catch any exception
            log.error("joinGame" + e.getMessage(), e);
        }

        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Game closeGame(Long gauid, Long uuid) throws LogicException {
        Game game = null;

        try{
            game = this.getGame(gauid);

            if(game.getState().equals(Consts.GAME_STATE_GAME)){
                if(uuid == game.getBlackUuid()){
                    game.setState(Consts.GAME_STATE_CLOSE);
                    game.setResolution("white win; black capitulated;");
                } else if(uuid == game.getWhiteUuid()){
                    game.setState(Consts.GAME_STATE_CLOSE);
                    game.setResolution("black win; white capitulated;");
                }
                em.merge(game);
            }
        } catch (LogicException le){
            throw le;
        } catch (Exception e){
            //Catch any exception
            log.error("closeGame" + e.getMessage(), e);
        }

        return game;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delGame(Long gauid) {
        Game game = null;

        try{
            game = em.find(Game.class, gauid);

            if(game.getState().equals("open")){
                em.remove(game);
            }
        }catch (Exception e){
            //Catch any exception
            log.error("delGame" + e.getMessage(), e);
        }
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
    public List<Game> getGamesFiltered(String field, String value) {
        List<Game> games = null;

        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Game> query = cb.createQuery(Game.class);
            Root<Game> root = query.from(Game.class);

            query.select(root);
            query.where(cb.equal(root.get(field), value));

            games = em.createQuery(query)
                    .getResultList();
        } catch(Exception e){
            //Catch any exception
            log.error("getGamesFiltered: " + e.getMessage(), e);

        }

        return games;
    }

    @Override
    public List<Game> getUserGames(Long uuid) throws LogicException {
        List<Game> games = null;

        if(em.find(User.class, uuid) == null){
            throw new LogicException(4L, "User with id " + uuid + " not found");
        }

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
