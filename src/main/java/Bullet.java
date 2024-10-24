package src.main.java;

import java.awt.*;

/**
 * Class containing methods for entity.
 */
public class Bullet extends Entity {

    private double angle;
    private int xChange;
    private int yChange;

    public int getXChange() {
        return xChange;
    }

    public int getYChange() {
        return yChange;
    }

    /**
     * Check whether the bullet is outside the game area.
     * @return true is the bullet is outside the game area, false otherwise 
     */
    public boolean isOutOfGameArea() {
        int[] pos = this.getPosition();
        int x = pos[0];
        int y = pos[1];

        if (x < 0 || y < 0 || x > Constants.getMapWidth() || y > Constants.getMapHeight()) {
            return true;
        }

        return false;
    }

    /**
     * Update position of the bullets.
     * @return true if bullet stays on the screen, false otherwise.
     */
    public boolean updatePosition() {
        int[] actPos = this.getPosition();
        this.setPosition(actPos[0] + this.getXChange(), actPos[1] + this.getYChange());
        
        return !this.isOutOfGameArea();
    }

    /**
     * Drawing method for the bullet.
     * @param g - Graphics used by Swing
     * @param p - Painter object, game panel
     */
    public void draw(Graphics g, Painter p) {
        g.drawImage(this.getImage(), this.getPosition()[0], this.getPosition()[1], p);
    }

    
    /**
     * Constructor for Bullet class.
     * @param x - starting position x-coordinate
     * @param y - starting position y-coordinate
     * @param angleRadians - angle it should be rotated at
     */
    public Bullet(int x, int y, double angleRadians) {
        this.setPosition(x, y);
        this.setImage("bulletTest");
        angle = angleRadians;
        xChange = (int) (Math.cos(angle) * (double) Constants.getBulletStep());
        yChange = (int) (Math.sin(angle) * (double) Constants.getBulletStep());
    }    
}
