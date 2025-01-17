package game;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Class containt all mechanics related to the player character.
 */
public class Player extends Entity {
    
    private long currency = 0;
    private boolean isHidden;

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
        if (x <= this.getCurrency()) {
            this.giveCurrency(-x);
            return true;
        }

        return false;
    }

    //hides the player from the screen (used only during ending)
    public void hide() {
        isHidden = true;
    }

    //ensures opponents will follow tank (used only during ending)
    public void moveTogetherWithTank(int x, int y) {
        this.setPosition(x, y);
    }
   
    /**
     * Draw Player character.
     * @param g - used by Swing
     */
    public void draw(Graphics g, JPanel p) {
        
        if (!this.isHidden) {
            int[] position = getPosition();
            g.drawImage(this.getImage(), position[0], position[1], p);
        }

        int height = Constants.getMapHeight();
        int width = Constants.getMapWidth();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Health: " + getHealthPoints(), width - 100, height + 20);
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
        this.getWeapon().setDamage(50);
        this.setHealthPoints(Constants.getPlayerHealthPoints());
        this.isHidden = false;
    }
}
