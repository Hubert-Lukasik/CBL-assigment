package unitTests;

import game.Map;
import game.Opponent;
import game.Phases;
import game.Player;
import game.Shop;
import game.TurretManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Class contains tests for Phases class.
 */
public class PhasesTest {
    Phases p;

    /**
     * Initialisation before tests.
     */
    @Before
    public void init() {
        p = new Phases(new Shop(new Player("test_char", 0, 0), new TurretManager()), new TurretManager(), new Map(), new Player("test_char", 0, 0));
    }

    @Test
    public void testDefendPhaseSetter() {
        p.startDefendPhase();
        Assert.assertEquals("Defend", p.getPhase());
        Assert.assertNotEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testPlanPhase() {
        p.startPlanPhase();
        Assert.assertEquals("Plan", p.getPhase());
        Assert.assertEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testLevelGetterandSetter() {
        p.setLevel(4L);
        Assert.assertEquals(4L, p.getLevel());
        p.setLevel(-1L);
        Assert.assertEquals(4L, p.getLevel());
        p.setLevel(0L);
        Assert.assertEquals(0L, p.getLevel());
    }

    @Test
    public void testLevelIncrease() {
        p.setLevel(2L);
        p.increaseLevel();
        Assert.assertEquals(p.getLevel(), 3L);
    }
}
