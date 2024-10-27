package src.main.java;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Class containing methods for entity.
 */
public class Bullet extends Entity {

    private int xChange;
    private int yChange;

    private boolean isExploding = false;
    private int explosionCount = 0;

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

        //To determine when bullets should vanish more accurately
        int leftUpperX = x;
        int leftUpperY = y;
        int rightBottomX = x + this.getImage().getWidth();
        int rightBottomY = y + this.getImage().getHeight();

        return (leftUpperX < 0 || leftUpperY < 0 
            || rightBottomX > Constants.getMapWidth() || rightBottomY > Constants.getMapHeight()); 
    }

    /**
     * Update position of the bullets.
     * @return true if bullet stays on the screen, false otherwise.
     */
    public boolean updatePosition() {
        int[] actPos = this.getPosition();
        if (!isExploding) {
            this.setPosition(actPos[0] + this.getXChange(), actPos[1] + this.getYChange());
            checkExplosion();
        } else {
            if (explosionCount > 4) {
                return true;
            }
            explosionCount++;
        }
        return !this.isOutOfGameArea();
    }


    /**
     * checks if the bullet should explode and damages opponents inside the explosion.
     */
    private void checkExplosion() {
        int[] curPos = this.getPosition();
        int width = this.getImage().getWidth();
        int height = this.getImage().getHeight();
        Rectangle hitbox = new Rectangle(curPos[0], curPos[1], width, height);
        for (Entity e : checkHit(hitbox)) {
            if (Opponent.getOpponents().contains(e)) {
                isExploding = true;
                e.takeDamage(50);
                this.setImage("bullet_exploding");
            }
        }
    }

    /**
     * Drawing method for the bullet.
     * @param g - Graphics used by Swing
     * @param p - Painter object, game panel
     */
    public void draw(Graphics g, JPanel p) {
        if (!(explosionCount > 4)) {
            g.drawImage(this.getImage(), this.getPosition()[0], this.getPosition()[1], p);
        }
    }

    
    /**
     * Constructor for Bullet class.
     * @param x - starting position x-coordinate
     * @param y - starting position y-coordinate
     * @param destX - y-coordinate of the destination
     * @param destY - y-coordinate of the destination
     */
    public Bullet(int x, int y, int destX, int destY) {
        this.setPosition(x, y);
        this.setImage("bullet");

        double yDifference = destY - y;
        double xDifference = destX - x;
        double step = Constants.getBulletStep();

        //Double can be inprecisive, check for xDifference == 0
        if (xDifference <= 0.001 && xDifference >= -0.001) {
            xChange = 0;
            yChange = (int) step;
        } else {
            double ratio = Math.abs(yDifference) / Math.abs(xDifference);
            yChange = (int) (step * (ratio / (ratio + 1.0)));
            xChange = (int) (step * (1.0 / (ratio + 1.0)));
        }

        //Set correct direction
        if (x > destX) {
            xChange *= -1;
        }

        if (y > destY) {
            yChange *= -1;
        }
         
    }    
}
