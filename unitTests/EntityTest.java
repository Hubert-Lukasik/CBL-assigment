package unitTests;

import game.Entity;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    
    /**
     * Set hps to -4.
     * Set position to (0, 0).
     */
    @Before
    public void setup() {
        obj.setHealthPoints(-4);
        obj.setPosition(0, 0);
        obj.setImage("tank");
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
        int[] pos = new int[2];
        pos[0] = 0;
        pos[1] = 0;

        int[] res = obj.getPosition();
        Assert.assertEquals(pos[0], res[0]);
        Assert.assertEquals(pos[1], res[1]);
    }

    @Test
    public void testPositionSetter() {
        obj.setPosition(3, -5);
        int[] pos = new int[2];
        pos[0] = 3;
        pos[1] = -5;
        int[] res = obj.getPosition();
        Assert.assertEquals(pos[0], res[0]);
        Assert.assertEquals(pos[1], res[1]);
    }

    @Test
    public void testImageGetter() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            String path = (new File("files", "tank.png")).getAbsolutePath();
            expected = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Test: Image test_char.png not found");
        }

        Assert.assertArrayEquals(expected.getPropertyNames(), obj.getImage().getPropertyNames());
    }

    @Test
    public void testImageSetter() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            expected = ImageIO.read(new File(
                new File("files", "turret.png").getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Test: Image test_char2.png not found");
        }

        obj.setImage("turret");
        Assert.assertArrayEquals(expected.getPropertyNames(), obj.getImage().getPropertyNames());
    }

    @Test
    public void testSettingNonExestingImage() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            expected = ImageIO.read(new File(
                new File("files", "tank.png").getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Test: Image test_char.png not found");
        }

        obj.setImage("nonExistingImage");
        Assert.assertArrayEquals(expected.getPropertyNames(), obj.getImage().getPropertyNames());
    }
}
