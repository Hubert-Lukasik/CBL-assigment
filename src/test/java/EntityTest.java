package src.test.java;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    
    /**
     * Set hps to -4.
     * Set position to (0, 0).
     */
    @Before
    public void setup() {
        obj.setHealthPoints(-4);
        obj.setPosition(0, 0);
        obj.setImage("test_char.png");
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

        double[] res = obj.getPosition();
        Assert.assertEquals(pos[0], res[0], 0.01);
        Assert.assertEquals(pos[1], res[1], 0.01);
    }

    @Test
    public void testPositionSetter() {
        obj.setPosition(3.2, -5.99);
        double[] pos = new double[2];
        pos[0] = 3.2;
        pos[1] = -5.99;
        double[] res = obj.getPosition();
        Assert.assertEquals(pos[0], res[0], 0.01);
        Assert.assertEquals(pos[1], res[1], 0.01);
    }

    @Test
    public void testImageGetter() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            expected = ImageIO.read(new File(
                new File("files", "test_char.png").getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Test: Image test_char.png not found");
        }

        Assert.assertEquals(expected, obj.getImage());
    }

    @Test
    public void testImageSetter() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            expected = ImageIO.read(new File(
                new File("files", "test_char2.png").getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Test: Image test_char2.png not found");
        }

        obj.setImage("test_char2");
        Assert.assertEquals(expected, obj.getImage());
    }

    @Test
    public void testSettingNonExestingImage() {
        java.awt.image.BufferedImage expected = new BufferedImage(1, 1, 1);
        try {
            expected = ImageIO.read(new File(
                new File("files", "test_char.png").getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("Test: Image test_char.png not found");
        }

        obj.setImage("nonExistingImage");
        Assert.assertEquals(expected, obj.getImage());
    }
}
