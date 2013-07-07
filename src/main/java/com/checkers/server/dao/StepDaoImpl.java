package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
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
@Repository("stepDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StepDaoImpl implements StepDao {

    static Logger log = Logger.getLogger(StepDaoImpl.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Step getStep(Long suid) {
        Step step = null;

        try{
            step = em.find(Step.class, suid);
        }catch(Exception e){
            //Catch any exception
            log.error("getStep: " + e.getMessage(), e);
        }

        return step;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void newStep(Step step) {
        try{
            if(step.getGame() != null){
                step.setGauid(step.getGame().getGauid());
            } else{
                if(step.getGauid() != null){
                    step.setGame(em.getReference(Game.class, step.getGauid()));
                } else{
                    throw new Exception("Attempt to create a step without a pointer to the game");
                }
            }

            if(step.getUser() != null){
                step.setUuid(step.getUser().getUuid());
            } else{
                if(step.getUuid() != null){
                    step.setUser(em.getReference(User.class, step.getUuid()));
                } else{
                    throw new Exception("Attempt to create a step without a pointer to the user. Service layer should do it");
                }
            }

            em.persist(step);

        }catch(Exception e){
            //Catch any exception
            log.error("newStep: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Step> getGameSteps(Long gauid) {
        List<Step> steps = null;

        try{
            steps = em.createQuery("SELECT s FROM Step s WHERE s.game.gauid = :gauid")
                    .setParameter("gauid", gauid)
                    .getResultList();
        }catch(Exception e){
            //Catch any exception
            log.error("getGameSteps: " + e.getMessage(), e);
        }

        return steps;
    }

    @Override
    public Step getGameLastStep(Long gauid) {
        Step step = null;

        try{
            step = (Step)em.createQuery("SELECT s FROM Step s ORDER BY s.suid DESC").setMaxResults(1).getSingleResult();
        }catch(Exception e){
            //Catch any exception
            log.error("getGameLastStep: " + e.getMessage(), e);
        }

           return step;
    }
}
