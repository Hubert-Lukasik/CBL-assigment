package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
 * Class responsible for drawing everything on the screen.
 */
public class Painter extends JPanel {

    private Player player;

    /**
     * Inform Painter class about player object.
     * @param player - player character object
     */
    public void informAboutPlayer(Player player) {
        this.player = player;
    }

    public void applyAnimation() {
        this.repaint();
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Map.draw(g, this);
        Opponent.draw(g, this);
        player.draw(g, this);
    }

    
}
