package src.test.java;


import org.junit.Assert;
import org.junit.Test;
import src.main.java.Player;

/**
 * Test player class features, mailny connected to shopping defences.
 */
public class PlayerTest {
    
    @Test
    public void testGettingCurrentAmountOfCurrency() {
        Player pl = new Player("test_char", 0, 0);
        pl.giveCurrency(5L);
        Assert.assertEquals(5L, pl.getCurrency());
    }

    @Test
    public void testGivingCurrency() {
        Player pl = new Player("test_char", 0, 0);
        pl.giveCurrency(6L);
        Assert.assertEquals(6L, pl.getCurrency());
        pl.giveCurrency(7L);
        Assert.assertEquals(13L, pl.getCurrency());
        pl.giveCurrency(-15L);
        Assert.assertEquals(-2L, pl.getCurrency());
    }

    @Test 
    public void testBuy() {
        Player pl = new Player("test_char", 0, 0);
        pl.giveCurrency(13L);
        Assert.assertEquals(true, pl.buy(12L));
        Assert.assertEquals(1L, pl.getCurrency());
        Assert.assertEquals(false, pl.buy(2L));
        Assert.assertEquals(1L, pl.getCurrency());
    }
}
