package src.test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.main.Entity;

/**
 * Dummy class to test Enity class. 
 */
class TestObject extends Entity {
} 

/**
* Tests for Entity class.
*/
public class EntityTest {
    static TestObject obj = new TestObject(); 
    
    @Before
    public void setup() {
        obj.setHealthPoints(-4);
        obj.setPosition(0, 0);
    }

    @Test
    public void testGetHealthPoints() {
        Assert.assertEquals(-4, obj.getHealthPoints());    
    }

    @Test
    public void testSetHealthPoints() {
        obj.setHealthPoints(69);
        Assert.assertEquals(69, obj.getHealthPoints());
    }

    @Test
    public void testTakingDamage() {
        obj.setHealthPoints(6);
        obj.takeDamage(2);
        Assert.assertEquals(4, obj.getHealthPoints());
    }

    @Test
    public void testBeingDead() {
        obj.setHealthPoints(1);
        obj.takeDamage(1);
        Assert.assertEquals(true, obj.isDead());
        obj.takeDamage(5);
        Assert.assertEquals(true, obj.isDead());
    }

    @Test
    public void testPositionGetter() {
        double[] pos = new double[2];
        pos[0] = 0;
        pos[1] = 0;
        Assert.assertEquals(pos, obj.getPosition());
    }

    @Test
    public void testPositionSetter() {
        obj.setPosition(3.2, -5.99);
        double[] pos = new double[2];
        pos[0] = 3.2;
        pos[1] = -5.99;
        obj.getPosition(pos, obj.getPosition());
    }
}
