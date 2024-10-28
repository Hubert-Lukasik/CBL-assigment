package unitTests;

import game.Turret;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Turret class.
 */
public class TurretTest {
    Turret tur;
    
    @Before
    public void init() {
        tur = new Turret(0, 0);
    }

    @Test
    public void turretConstructorTest() {
        int[] pos = new int[2];
        pos[0] = 0;
        pos[1] = 0;
        Assert.assertArrayEquals(pos, tur.getPosition());
        Assert.assertEquals(false, tur.isOnline());
        Assert.assertNotEquals(0, tur.getHealthPoints());
    }

    @Test
    public void makingTurretOnlineTest() {
        Assert.assertEquals(false, tur.isOnline());
        tur.setOnline(true);
        Assert.assertEquals(true, tur.isOnline());
    }
}