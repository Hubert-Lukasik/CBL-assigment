package unitTests;

import game.Map;
import game.Opponent;
import game.Painter;
import game.Phases;
import game.Player;
import game.Shop;
import game.TurretManager;
import org.junit.Assert;
import org.junit.Test;

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
        Shop s = new Shop(new Player("tank", 0, 0), new TurretManager());
        Phases p = new Phases(s, new TurretManager(), new Map(), new Player("tank", 0, 0));
        Opponent.informAboutTurretManager(new TurretManager());
        Opponent.informAboutPlayer(new Player("tank", 0, 0));
        Opponent.informAboutGamePanel(new Painter(new Player("tank", 0, 0), 
            new Map(), new Shop(new Player("tank", 0, 0), 
                new TurretManager()), 
            new TurretManager()));
        p.startDefendPhase();
        Assert.assertEquals(false, s.isShown());
        p.startPlanPhase();
        Assert.assertEquals(true, s.isShown());
        p.startDefendPhase();
        Assert.assertEquals(false, s.isShown());
    }
}
