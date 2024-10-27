package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
* Class describing all methods and variables relating to turrets.
*/
public class Turret extends Entity {
    private boolean online;

    /**
     * Sets turrets online or offline.
     * @param isOnline - true if turret should be online, false otherwise
     */
    public void setOnline(boolean isOnline) {
        online = isOnline;
    }

    /**
     * Checks whether the turret is online.
     * @return true if turret is online, false otherwise
     */
    public boolean isOnline() {
        return online;
    }

    
    /**
     * Method to draw the turret.
     * @param g - used by Swing
     * @param p - game panel
     */
    public void draw(Graphics g, JPanel p) {
        int[] position = getPosition();
    
        g.drawImage(this.getImage(), position[0], position[1], p);
    }

    /**
     * Constructor for Turret class.
     * @param x - coordinate x of the turret
     * @param y - coordinate y of the turret
     */
    public Turret(int x, int y) {
        this.setPosition(x, y);
        this.setImage("turret");
        this.setHealthPoints(Constants.getTurretHealthPoints());
        this.addCollision();
    }
}
