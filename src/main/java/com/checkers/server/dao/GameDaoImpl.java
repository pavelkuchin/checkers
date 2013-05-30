package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.proxy.GameProxy;
import com.checkers.server.beans.User;
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
    @PersistenceContext
    private EntityManager em;

    @Override
    public Game getGame(Long gauid) {
        return em.find(Game.class, gauid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void newGame(Game game) {
        em.persist(game);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Game newGame(GameProxy gameProxy) {
        Game game = new Game(gameProxy);

        game.setWhite(em.getReference(User.class, gameProxy.getWhiteUuid()));
        game.setBlack(em.getReference(User.class, gameProxy.getBlackUuid()));

        this.newGame(game);

        return game;
    }

    @Override
    public List<Game> getGames() {
        return em.createQuery("SELECT g FROM Game g").getResultList();
    }

    @Override
    public List<Game> getUserGames(Long uuid) {
        return em.createQuery("SELECT g FROM Game g WHERE g.white.uuid = :uuid OR g.black.uuid = :uuid")
                .setParameter("uuid", uuid)
                .getResultList();
    }
}
