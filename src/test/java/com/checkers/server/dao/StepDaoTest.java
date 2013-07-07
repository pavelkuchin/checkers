package com.checkers.server.dao;

import com.checkers.server.beans.Step;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Tests for {@link StepDaoImpl}
 *
 * @author Pavel Kuchin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-context.xml")
public class StepDaoTest {
    @Autowired
    UserDao userDao;

    @Autowired
    GameDao gameDao;

    @Autowired
    StepDao stepDao;

    @Test
    public void newStep(){
        Step step = new Step();

        step.setSuid(null);

        step.setGame(gameDao.getGame(1L));
        step.setUser(userDao.getUser(1L));

        step.setStep("e3-d4");

        step.setCreated(new Date());

        stepDao.newStep(step);

        Assert.assertNotNull("Step's object doesn't persist.", step.getSuid());
    }

    @Test
    public void newStepProxy(){
        Step step = new Step();

        step.setSuid(null);

        step.setGauid(1L);
        step.setUuid(1L);

        step.setStep("d6-c5");

        step.setCreated(new Date());

        stepDao.newStep(step);

        Assert.assertNotNull("Step's proxy is null", step);
        Assert.assertNotNull("Step's proxy object doesn't persist", step.getSuid());
    }

    @Test
    public void getStep(){
        Step step = new Step();

        step.setSuid(null);

        step.setGauid(1L);
        step.setUuid(1L);

        step.setStep("c5:e3:g5");

        step.setCreated(new Date());

        stepDao.newStep(step);

        Step obtainedStep = stepDao.getStep(step.getSuid());

        Assert.assertNotNull("Obtained step is null", step);
        Assert.assertEquals("Obtained step has unexpected suid", step.getSuid(), obtainedStep.getSuid());
    }

    @Test
    public void getLastStep() throws InterruptedException {
        Step step1 = new Step();
        Step step2 = new Step();

        step1.setSuid(null);

        step1.setGauid(1L);
        step1.setUuid(1L);

        step1.setStep("g3-f4");

        step1.setCreated(new Date());

        stepDao.newStep(step1);

        Step obtainedStep = stepDao.getGameLastStep(1L);

        Assert.assertEquals("[First time] Last step is incorrect", step1.getSuid(), obtainedStep.getSuid());

        TimeUnit.SECONDS.sleep(1);

        step2.setSuid(null);

        step2.setGauid(1L);
        step2.setUuid(1L);

        step2.setStep("f4:h6:f8:c5:g1");

        step2.setCreated(new Date());

        stepDao.newStep(step2);

        obtainedStep = stepDao.getGameLastStep(1L);

        Assert.assertEquals("[Second time] Last step is incorrect", step2.getSuid(), obtainedStep.getSuid());

    }

    @Test
    public void getGameSteps(){
        List<Step> steps = stepDao.getGameSteps(1L);

        Assert.assertNotNull("Game steps list is null", steps);
        Assert.assertNotEquals("Game steps size is incorrect", 0, steps.size());
    }
}
