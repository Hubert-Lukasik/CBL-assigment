package src.main.java;

import java.awt.event.*;

/**
 * Class is responsible for animating player character.
 */
public class PlayerAnimation implements KeyListener {
    private static Painter gamePanel;
    private static Player player;

    public static void informAboutGamePanel(Painter p) {
        gamePanel = p;
    }

    public static void informAboutPlayer(Player pl) {
        player = pl;
    }

    /**
     * KeyListener method responsible for moving the player character.
     */
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        
        int[] pos = player.getPosition();

        if (key == 'w') {
            player.setPosition(pos[0], pos[1] - 10);
        }

        if (key == 's') {
            player.setPosition(pos[0], pos[1] + 10);
        }

        if (key == 'a') {
            player.setPosition(pos[0] - 10, pos[1]);
        }

        if (key == 'd') {
            player.setPosition(pos[0] + 10, pos[1]);
        }

        gamePanel.applyAnimation();

    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}
    
    
}
