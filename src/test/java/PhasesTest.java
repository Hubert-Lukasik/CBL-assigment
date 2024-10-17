package src.test.java;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.Opponent;
import src.main.java.Phases;

public class PhasesTest {

    @Test
    public void testPhaseGetter() {
        String exp = "";
        Assert.assertEquals(exp, Phases.getPhase());
    }

    @Test
    public void testDefendPhaseSetter() {
        Phases.startDefendPhase();
        Assert.assertEquals("Defend", Phases.getPhase());
        Assert.assertEquals(Opponent.howManyOpponents(), 1);
    }

    @Test
    public void testPlanPhase() {
        Phases.startPlanPhase();
        Assert.assertEquals("Plan", Phases.getPhase());
        Assert.assertEquals(Opponent.howManyOpponents(), 0);

    }
}
