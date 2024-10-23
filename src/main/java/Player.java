package src.main.java;

import java.awt.*;

/**
 * Class containt all mechanics related to the player character.
 */
public class Player extends Entity {
    
    private long currency = 0;

    public void giveCurrency(long x) {
        this.currency += x;
    }

    public long getCurrency() {
        return this.currency;
    }

    /**
     * Checks whether an item for x amount of currency can be bought.
     * @param x - cost of the item
     * @return - true if player can buy item and deduce the cost, return false otherwise
     */
    public boolean buy(long x) {
        if (x <= currency) {
            this.giveCurrency(-x);
            return true;
        }

        return false;
    }
   
    /**
     * Draw Player character.
     * @param g - used by Swing
     */
    public void draw(Graphics g, Painter p) {
        int[] position = getPosition();

        g.drawImage(this.getImage(), position[0], position[1], p);
    }


    /**
     * Constructor for Player instance.
     * @param filename - name of the file containng starting image (.png file) for player
     * @param positionX - starting position x coordinate
     * @param positionY - starting position y coordinate
     */
    public Player(String filename, int positionX, int positionY) {
        this.setImage(filename);
        this.setPosition(positionX, positionY);
    }
}

