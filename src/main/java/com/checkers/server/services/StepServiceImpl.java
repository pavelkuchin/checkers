package com.checkers.server.services;

import com.checkers.server.beans.Step;
import com.checkers.server.beans.proxy.StepProxy;
import com.checkers.server.dao.StepDao;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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

    ConcurrentMap<Long, Object> events = new ConcurrentHashMap<Long, Object>();

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Step getStep(Long suid) {
        return stepDao.getStep(suid);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Step newStep(StepProxy stepProxy) {
        Step result;

        Object event;

        synchronized (events){
            if(!events.containsKey(stepProxy.getGauid())){
                events.put(stepProxy.getGauid(), new Object());
            }
            event = events.get(stepProxy.getGauid());
        }

        synchronized (event) {
            result = stepDao.newStep(stepProxy);

            if(result.getSuid() != null){
                //We notify all listeners about new step in the DB
                event.notifyAll();
            }
        }

        return result;
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
