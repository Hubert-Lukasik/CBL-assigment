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
    public static void setup() {
        obj.setHealthPoints(-4);
    }



    @Test
    public void testGetHealthPoints() {
        Assert.assertEquals(0, obj.getHealthPoints());    
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
}
