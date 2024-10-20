package src.test.java;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.Opponent;

/**
 * Unit tests for Opponent class.
 */
public class OpponentTest {
    
    @Test
    public void testGettingNumOfOpponents() {
        Opponent.clearOpponentsArray();
        Assert.assertEquals(0, Opponent.howManyOpponents());
    }

    @Test
    public void testAddingOpponent() {
        Opponent.clearOpponentsArray();
        Opponent.addOpponent();
        Assert.assertEquals(1, Opponent.howManyOpponents());
    }


}
