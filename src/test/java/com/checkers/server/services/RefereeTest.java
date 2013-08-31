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

    @Test
    public void russianGraphRefereeVariant2Test() throws CheckersException {
        Referee referee = new RussianGraphRefereeImpl();

        //1
        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-g5", FigureColor.BLACK));

        //2
        Assert.assertTrue(referee.checkStep("g3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e7-f6", FigureColor.BLACK));

        //3
        Assert.assertTrue(referee.checkStep("b2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-e5", FigureColor.BLACK));

        //4
        Assert.assertTrue(referee.checkStep("b4-a5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e5:g3", FigureColor.BLACK));

        //5
        Assert.assertTrue(referee.checkStep("h2:f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-f6", FigureColor.BLACK));

        //6
        Assert.assertTrue(referee.checkStep("c3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h8-g7", FigureColor.BLACK));

        //7
        Assert.assertTrue(referee.checkStep("d2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5-h4", FigureColor.BLACK));

        //8
        Assert.assertTrue(referee.checkStep("a1-b2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-g5", FigureColor.BLACK));

        //9
        Assert.assertTrue(referee.checkStep("d4-e5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b6-c5", FigureColor.BLACK));

        //10
        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d8-e7", FigureColor.BLACK));

        //11
        Assert.assertTrue(referee.checkStep("c1-d2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-f6", FigureColor.BLACK));

        //12
        Assert.assertTrue(referee.checkStep("e5:g7", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c5-d4", FigureColor.BLACK));

        //13
        Assert.assertTrue(referee.checkStep("e3:c5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5:e3:c1", FigureColor.BLACK));

        //14
        Assert.assertTrue(referee.checkStep("g7-h8", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6-e5", FigureColor.BLACK));

        //15
        Assert.assertTrue(referee.checkStep("h8:c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c1-g5", FigureColor.BLACK));

        //16
        Assert.assertTrue(referee.checkStep("a5-b6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c7:a5", FigureColor.BLACK));

        //17
        Assert.assertTrue(referee.checkStep("c5-d6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e7:c5", FigureColor.BLACK));

        //18
        Assert.assertTrue(referee.checkStep("b4:d6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5-d8", FigureColor.BLACK));

        //19
        Assert.assertTrue(referee.checkStep("c3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d8-c7", FigureColor.BLACK));

        //20
        Assert.assertTrue(referee.checkStep("d4-e5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f8-e7", FigureColor.BLACK));

        //21
        Assert.assertTrue(referee.checkStep("d6:f8", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c7:f4", FigureColor.BLACK));

        //22
        Assert.assertTrue(referee.checkStep("b2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f4-e5", FigureColor.BLACK));

        //23
        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a5:c3", FigureColor.BLACK));

        //24
        Assert.assertTrue(referee.checkStep("f8-c5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e5-f4", FigureColor.BLACK));

        //25
        Assert.assertTrue(referee.checkStep("g1-h2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f4-c1", FigureColor.BLACK));

        //26
        Assert.assertTrue(referee.checkStep("f2-g3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h4:f2", FigureColor.BLACK));

        //27
        Assert.assertTrue(referee.checkStep("e1:g3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c3-d2", FigureColor.BLACK));

        //28
        Assert.assertTrue(referee.checkStep("g3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d2-e1", FigureColor.BLACK));

        //29
        Assert.assertTrue(referee.checkStep("f4-e5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e1-h4", FigureColor.BLACK));

        //30
        Assert.assertTrue(referee.checkStep("c5-f8", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c1-g5", FigureColor.BLACK));

        //31
        Assert.assertTrue(referee.checkStep("a3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a7-b6", FigureColor.BLACK));

        //32
        Assert.assertTrue(referee.checkStep("b4-a5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5-e3", FigureColor.BLACK));

        //33
        Assert.assertTrue(referee.checkStep("a5:c7", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b8:d6:f4", FigureColor.BLACK));

        System.out.println(referee.toString());
    }

    @Test
    public void russianGraphRefereeVariant3Test() throws CheckersException {
        Referee referee = new RussianGraphRefereeImpl();

        //1
        Assert.assertTrue(referee.checkStep("c3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6-c5", FigureColor.BLACK));

        //2
        Assert.assertTrue(referee.checkStep("b2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c7-d6", FigureColor.BLACK));

        //3
        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b6-a5", FigureColor.BLACK));

        //4
        Assert.assertTrue(referee.checkStep("d4:b6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a5:c7", FigureColor.BLACK));

        //5
        Assert.assertTrue(referee.checkStep("a1-b2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-e5", FigureColor.BLACK));

        //6
        Assert.assertTrue(referee.checkStep("b2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-f6", FigureColor.BLACK));

        //7
        Assert.assertTrue(referee.checkStep("e3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h8-g7", FigureColor.BLACK));

        //8
        Assert.assertTrue(referee.checkStep("g3-h4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h6-g5", FigureColor.BLACK));

        //9
        Assert.assertTrue(referee.checkStep("f2-g3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-h6", FigureColor.BLACK));

        //10
        Assert.assertTrue(referee.checkStep("d2-e3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a7-b6", FigureColor.BLACK));

        //11
        Assert.assertTrue(referee.checkStep("b4-a5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6-c5", FigureColor.BLACK));

        //12
        Assert.assertTrue(referee.checkStep("g3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e5:g3", FigureColor.BLACK));

        //13
        Assert.assertTrue(referee.checkStep("h2:f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e7-d6", FigureColor.BLACK));

        //14
        Assert.assertTrue(referee.checkStep("g1-h2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6-e5", FigureColor.BLACK));

        //15
        Assert.assertTrue(referee.checkStep("d4:f6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g5:e7", FigureColor.BLACK));

        //16
        Assert.assertTrue(referee.checkStep("e1-d2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b8-a7", FigureColor.BLACK));

        //17
        Assert.assertTrue(referee.checkStep("c3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f8-g7", FigureColor.BLACK));

        //18
        Assert.assertTrue(referee.checkStep("h2-g3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("g7-f6", FigureColor.BLACK));

        //19
        Assert.assertTrue(referee.checkStep("c1-b2", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h6-g5", FigureColor.BLACK));

        //20
        Assert.assertTrue(referee.checkStep("f4:h6", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6-e5", FigureColor.BLACK));

        //21
        Assert.assertTrue(referee.checkStep("b2-c3", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c5-b4", FigureColor.BLACK));

        //22
        Assert.assertTrue(referee.checkStep("a3:c5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c7-d6", FigureColor.BLACK));

        //23
        Assert.assertTrue(referee.checkStep("a5:c7", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d6:b4", FigureColor.BLACK));

        //24
        Assert.assertTrue(referee.checkStep("c3:a5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("d8:b6", FigureColor.BLACK));

        //25
        Assert.assertTrue(referee.checkStep("a5:c7", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e5:c3:e1", FigureColor.BLACK));

        //26
        Assert.assertTrue(referee.checkStep("c7-b8", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("e1-c3", FigureColor.BLACK));

        //27
        Assert.assertTrue(referee.checkStep("g3-f4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("c3-b4", FigureColor.BLACK));

        //28
        Assert.assertTrue(referee.checkStep("e3-d4", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("b4-a3", FigureColor.BLACK));

        //29
        Assert.assertTrue(referee.checkStep("h4-g5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("f6:h4", FigureColor.BLACK));

        //30
        Assert.assertTrue(referee.checkStep("f4-g5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("h4:f6", FigureColor.BLACK));

        //31
        Assert.assertTrue(referee.checkStep("d4-c5", FigureColor.WHITE));
        Assert.assertTrue(referee.checkStep("a3:d6", FigureColor.BLACK));

        System.out.println(referee.toString());
    }

}
