package src.main.java;

import java.awt.*;

/**
 * Class containt all mechanics related to the player character.
 */
public class Player extends Entity{

    /**
     * Draw Player character.
     * @param g - used by Swing
     */
    public void draw(Graphics g, Painter p) {
        int[] position = getPosition();

        g.drawImage(this.getImage(), position[0], position[1], p);
    }
}

