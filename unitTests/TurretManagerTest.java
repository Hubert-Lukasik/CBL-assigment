package src.test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.java.TurretManager;

/**
 * Tests for TurretManager.
 */
public class TurretManagerTest {

    private TurretManager turMan;
    
    @Before
    public void init() {
        turMan = new TurretManager();
    }

    @Test
    public void testAmountOfTurretsGetter() {
        Assert.assertEquals(0, turMan.getTurretsAmount());
    }

    @Test
    public void testAddingTurrets() {
        Assert.assertEquals(0, turMan.getTurretsAmount());
        turMan.addTurret(0, 0);
        Assert.assertEquals(1, turMan.getTurretsAmount());
    }

    @Test
    public void testFindingNearestTurret() {
        int[] exp = new int[2];
        exp[0] = -1;
        exp[1] = -1;
        
        Assert.assertArrayEquals(exp, turMan.nearestTurret(-4, -4));
        
        turMan.addTurret(0, 0);

        exp[0] = 0;
        exp[1] = 0;
        Assert.assertArrayEquals(exp, turMan.nearestTurret(3, 3));

        turMan.addTurret(4, 5);

        exp[0] = 4;
        exp[1] = 5;
        Assert.assertArrayEquals(exp, turMan.nearestTurret(4, 0));
        Assert.assertArrayEquals(exp, turMan.nearestTurret(100, 100));
        Assert.assertArrayEquals(exp, turMan.nearestTurret(3, 5));
    }

    @Test
    public void testStartingDefendAndPlanPhases() {
        turMan.startPlanPhase();
        Assert.assertEquals(0, turMan.howManyTurretsOnline());
        turMan.addTurret(1, 1);
        Assert.assertEquals(0, turMan.howManyTurretsOnline());
        turMan.startDefendPhase();
        Assert.assertEquals(1, turMan.howManyTurretsOnline());
    }
}
