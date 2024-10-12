package src.test.java;

import org.junit.Assert;
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
    @Test
    public void testGetHealthPoints() {
        TestObject obj = new TestObject();
        Assert.assertEquals(0, obj.getHealthPoints());    
    }

    @Test
    public void testSetHealthPoints() {
        TestObject obj = new TestObject();
        obj.setHealthPoints(69);
        Assert.assertEquals(69, obj.getHealthPoints());
    }
}
