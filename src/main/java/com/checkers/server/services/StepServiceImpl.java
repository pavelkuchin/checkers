package com.checkers.server.services;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.beans.User;
import com.checkers.server.dao.GameDao;
import com.checkers.server.dao.StepDao;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("stepService")
public class StepServiceImpl implements StepService {
    @Autowired
    private StepDao stepDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameDao gameDao;

    ConcurrentMap<Long, Object> events = new ConcurrentHashMap<Long, Object>();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Step getStep(Long suid) {
        return stepDao.getStep(suid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Step newStep(Step step) throws Exception {
        Object event;

        Game game = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userDao.getUserByLogin(name);

        if(step.getGame() == null){
            game = gameDao.getGame(step.getGauid());
        } else{
            game = step.getGame();
        }

        // Do step Login
        // Game should have status - 'game'
        if(!game.getState().equals("game")){
            //TODO Exceptions Alarm System
            throw new Exception("You can't make step in 'close' or 'open' game. Game should be in 'game' state.");
        }

        // You can't make two steps, one by one. It is obviously.
        if(stepDao.getGameLastStep(step.getGauid()).getUuid().equals(user.getUuid())){
            //TODO Exceptions Alarm System
            throw new Exception("You made your move. Let the opponent to make a move.");
        }

        // Async Login
        step.setUuid(null);

        step.setCreated(new Date());

        step.setUser(user);

        synchronized (events){
            if(!events.containsKey(step.getGauid())){
                events.put(step.getGauid(), new Object());
            }
            event = events.get(step.getGauid());
        }

        synchronized (event) {
            stepDao.newStep(step);

            if(step.getSuid() != null){
                //We notify all listeners about new step in the DB
                event.notifyAll();
            }
        }

        return step;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Step> getGameSteps(Long gauid) {
        return stepDao.getGameSteps(gauid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Step getGameLastStep(Long gauid) {
        return stepDao.getGameLastStep(gauid);
    }

    @Override
    public Step getAsyncGameLastStep(Long gauid, String username) throws InterruptedException {
        Step result = null;

        Object event;

        synchronized (events){
            if(!events.containsKey(gauid)){
                events.put(gauid, new Object());
            }
            event = events.get(gauid);
        }

        synchronized (event){
            while(result == null){
                Step lastStep = stepDao.getGameLastStep(gauid);
                if(lastStep != null && !lastStep.getUser().getLogin().equals(username)){
                    result = lastStep;
                } else{
                    //We are waiting for object creation
                    event.wait();
                }
            }
        }
            return result;
    }

}
