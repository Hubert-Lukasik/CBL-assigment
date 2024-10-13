package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
 * Class containt all mechanics related to the player character.
 */
public class Player extends Entity {
    


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] position = this.getPosition();

        g.drawImage(this.getImage(), position[0], position[1], this);
    }
}
