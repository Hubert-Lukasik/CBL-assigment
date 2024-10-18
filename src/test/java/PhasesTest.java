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
        Phases p = new Phases();
        p.startDefendPhase();
        Assert.assertEquals("Defend", p.getPhase());
        Assert.assertNotEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testPlanPhase() {
        Phases p = new Phases();
        p.startPlanPhase();
        Assert.assertEquals("Plan", p.getPhase());
        Assert.assertEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testLevelGetterandSetter() {
        Phases p = new Phases();
        p.setLevel(4L);
        Assert.assertEquals(4L, p.getLevel());
        p.setLevel(-1L);
        Assert.assertEquals(4L, p.getLevel());
        p.setLevel(0L);
        Assert.assertEquals(0L, p.getLevel());
    }

    @Test
    public void testLevelIncrease() {
        Phases p = new Phases();
        p.setLevel(2L);
        p.increaseLevel();
        Assert.assertEquals(p.getLevel(), 3L);
    }
}
