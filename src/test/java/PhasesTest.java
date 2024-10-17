package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.Opponent;
import src.main.java.Phases;

/**
 * Class contains tests for Phases class.
 */
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
        Phases.setLevel(-1L);
        Assert.assertEquals(4L, Phases.getLevel());
        Phases.setLevel(0L);
        Assert.assertEquals(0L, Phases.getLevel());
    }

    @Test
    public void testLevelIncrease() {
        Phases.setLevel(2L);
        Phases.increaseLevel();
        Assert.assertEquals(Phases.getLevel(), 3L);
    }
}
