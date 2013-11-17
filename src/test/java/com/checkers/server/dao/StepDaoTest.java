package com.checkers.server.dao;

import com.checkers.server.beans.Game;
import com.checkers.server.beans.Step;
import com.checkers.server.exceptions.ApplicationException;
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
    public void newStep() throws ApplicationException {
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name 101");
        game.setDescription("Game description");

        game.setState("game");
        game.setType("offline");
        game.setBoard("8x8");

        game.setWhite(userDao.getUser(2L));
        game.setBlack(userDao.getUser(1L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Step step = new Step();

        step.setSuid(null);

        step.setGame(game);
        step.setUser(userDao.getUser(1L));

        step.setStep("e3-d4");

        step.setCreated(new Date());

        stepDao.newStep(step);

        Assert.assertNotNull("Step's object doesn't persist.", step.getSuid());
    }

    @Test
    public void newStepLongAsObject() throws ApplicationException {
        Game game = new Game();

        game.setGauid(null);

        game.setName("Game name 202");
        game.setDescription("Game description");

        game.setState("game");
        game.setType("offline");
        game.setBoard("8x8");

        game.setWhite(userDao.getUser(2L));
        game.setBlack(userDao.getUser(1L));

        game.setModified(new Date());
        game.setCreated(new Date());
        game.setLastStep(new Date());

        gameDao.newGame(game);

        Step step = new Step();

        step.setSuid(null);

        step.setGauid(game.getGauid());
        step.setUuid(1L);

        step.setStep("d6-c5");

        step.setCreated(new Date());

        stepDao.newStep(step);

        Assert.assertNotNull("StepLongAsObject's is null", step);
        Assert.assertNotNull("StepLongAsObject's object doesn't persist", step.getSuid());
    }

    @Test
    public void getStep() throws ApplicationException {
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
    public void getLastStep() throws InterruptedException, ApplicationException {
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
