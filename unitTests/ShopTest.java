package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.Map;
import src.main.java.Phases;
import src.main.java.Player;
import src.main.java.Shop;
import src.main.java.TurretManager;

/**
 * Tests for shop with defences.
 */
public class ShopTest {
    /*
    MANUAL TESTING OF THE SHOP CLASS
    1. Is the shop easy to distinguish from the game screen?
    2. Is turret icon visible?
    3. Is tank icon visible?
    4. Is current amount of currency shown?
    5. Is it possible to move turrets to their positions?
    6. Is amount of currency deducted when shopping?
    */

    @Test
    public void isShownOnlyDuringPlanPhase() {
        Shop s = new Shop(new Player("test_char", 0, 0), new TurretManager());
        Phases p = new Phases(s, new TurretManager(), new Map(), new Player("test_char", 0, 0));
        p.startDefendPhase();
        Assert.assertEquals(false, s.isShown());
        p.startPlanPhase();
        Assert.assertEquals(true, s.isShown());
        p.startDefendPhase();
        Assert.assertEquals(false, s.isShown());
    }
}