package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.beans.User;
import com.checkers.server.beans.proxy.StepProxy;
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
            em.persist(step);
        }catch(Exception e){
            //Catch any exception
            log.error("newStep: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Step newStep(StepProxy stepProxy) {
        Step step = new Step(stepProxy);

        try{
            step.setGame(em.getReference(Game.class, stepProxy.getGauid()));
            step.setUser(em.getReference(User.class, stepProxy.getUuid()));
        }catch(Exception e){
            //Catch any exception
            log.error("newStep: " + e.getMessage(), e);
        }

        this.newStep(step);

        return step;
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
