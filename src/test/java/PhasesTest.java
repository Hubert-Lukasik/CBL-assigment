package src.test.java;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.Opponent;
import src.main.java.Phases;

public class PhasesTest {

    @Test
    public void testDefendPhaseSetter() {
        Phases.startDefendPhase();
        Assert.assertEquals("Defend", Phases.getPhase());
        Assert.assertNotEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testPlanPhase() {
        Phases.startPlanPhase();
        Assert.assertEquals("Plan", Phases.getPhase());
        Assert.assertEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testLevelGetterandSetter() {
        Phases.setLevel(4L);
        Assert.assertEquals(4L, Phases.getLevel());
    }
}
