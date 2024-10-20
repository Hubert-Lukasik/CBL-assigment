package src.test.java;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.Player;

/**
 * Test player class features, mailny connected to shopping defences.
 */
public class PlayerTest {
    
    @Test
    public void testGettingCurrentAmountOfCurrency() {
        Player pl = new Player();
        pl.giveCurrency(5);
        Assert.assertEquals(5, pl.getCurrency());
    }

    @Test
    public void testGivingCurrency() {
        Player pl = new Player();
        pl.giveCurrency(6);
        Assert.assertEquals(6, pl.getCurrency());
        pl.giveCurrency(7);
        Assert.assertEquals(13, pl.getCurrency());
        pl.giveCurrency(-15);
        Assert.assertEquals(-2, pl.getCurrency());
    }

    @Test 
    public void testBuy() {
        Player pl = new Player();
        pl.giveCurrency(13);
        Assert.assertEquals(true, pl.buy(12));
        Assert.assertEquals(1, pl.getCurrency);
        Assert.assertEquals(false, pl.buy(2));
        Assert.assertEquals(1, pl.getCurrency());
    }
}
