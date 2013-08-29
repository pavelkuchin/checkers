package com.checkers.server.services;

import com.checkers.server.exceptions.CheckersException;
import com.checkers.server.services.referee.Referee;
import com.checkers.server.services.referee.RussianGraphRefereeImpl;
import com.checkers.server.services.referee.graph.FigureColor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class RefereeTest {

    @Test
    public void russianGraphRefereeBasicTest() throws CheckersException {
        Referee referee = new RussianGraphRefereeImpl();

        Assert.assertTrue(referee.checkStep("e3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6-e5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("a3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h6-g5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("b4-c5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5-f4", FigureColor.BLACK));

        System.out.println(referee.toString());
    }


    @Test
    public void russianGraphRefereeVariant1Test() throws CheckersException {
        Referee referee = new RussianGraphRefereeImpl();

        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-e5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("e3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b6-a5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("f2-e3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a5:c3", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("b2:d4:f6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e7:g5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("a3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5-h4", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("g1-f2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a7-b6", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("b4-a5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b6-c5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("a1-b2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-f6", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("f4-g5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h6:f4", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("e3:g5:e7", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d8:f6", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("g3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-e5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("f2-e3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e5:g3", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("h2:f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f8-g7", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("d2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c5-b4", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("e1-f2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b4:d2", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("a5-b6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c7:a5", FigureColor.BLACK));

        Assert.assertTrue(referee.checkStep("f2-g3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h4:f2:d4", FigureColor.BLACK));

        System.out.println(referee.toString());
    }
}
