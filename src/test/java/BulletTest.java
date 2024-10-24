package src.test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.java.Bullet;
import src.main.java.Constants;

/**
 * Tests for bullets.
 */
public class BulletTest {

    Bullet bullet;
    
    @Before
    public void init() {
        bullet = new Bullet(300, 300, 0);
    }

    @Test
    public void directionTest() {
        Assert.assertEquals(0, bullet.getYChange());
        Assert.assertEquals(10, bullet.getXChange());
    }

    @Test
    public void isOutOfBounds() {
        Assert.assertEquals(false, bullet.isOutOfGameArea());
        bullet.setPosition(-1, -1);
        Assert.assertEquals(true, bullet.isOutOfGameArea());
        bullet.setPosition(Constants.getMapWidth() + 1, 0);
        Assert.assertEquals(true, bullet.isOutOfGameArea());
        bullet.setPosition(0, Constants.getMapHeight() + 1);
        Assert.assertEquals(true, bullet.isOutOfGameArea());
        bullet.setPosition(-1, 200);
        Assert.assertEquals(true, bullet.isOutOfGameArea());
        bullet.setPosition(200, -1);
    }
}
