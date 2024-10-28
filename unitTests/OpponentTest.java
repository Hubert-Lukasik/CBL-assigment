package unitTests;

import game.Opponent;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for Opponent class.
 */
public class OpponentTest {
    
    @Test
    public void testGettingNumOfOpponents() {
        Opponent.killOpponents();
        Assert.assertEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testAddingOpponent() {
        Opponent.killOpponents();
        Opponent.addOpponent();
        Assert.assertEquals(1, Opponent.howManyOpponents());
    }


}
