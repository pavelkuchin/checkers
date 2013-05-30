package com.checkers.server.services;

import com.checkers.server.beans.Step;
import com.checkers.server.beans.proxy.StepProxy;
import com.checkers.server.dao.StepDao;
import com.checkers.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("stepService")
public class StepServiceImpl implements StepService {
    @Autowired
    private StepDao stepDao;

    @Override
    public Step getStep(Long suid) {
        return stepDao.getStep(suid);
    }

    @Override
    public Step newStep(StepProxy stepProxy) {
        return stepDao.newStep(stepProxy);
    }

    @Override
    public List<Step> getGameSteps(Long gauid) {
        return stepDao.getGameSteps(gauid);
    }

    @Override
    public Step getGameLastStep(Long gauid) {
        return stepDao.getGameLastStep(gauid);
    }
}
