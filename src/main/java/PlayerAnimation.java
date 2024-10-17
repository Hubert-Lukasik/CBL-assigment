package src.main.java;

import java.awt.event.*;
import javax.swing.*;

/**
 * Class is responsible for animating player character.
 */
public class PlayerAnimation implements KeyListener, ActionListener {
    private static Painter gamePanel;
    private static Player player;
    private static boolean right = false;
    private static boolean left = false;
    private static boolean up = false;
    private static boolean down = false;
    private static int step = 10;

    private static Timer checkPlayerMovement;

    /**
     * 
     */
    public static String getDirection(boolean up, boolean right, boolean down, boolean left) {
        String direction = "";

        //exclude situation when two opposing keys are pressed
        if ((up && down) || (left && right)) {
            return "";
        }

        if (up) {
            direction += 'n';
        } 
        
        if (down) {
            direction += 's';
        }

        if (left) {
            direction += 'w';
        }

        if (right) {
            direction += 'e';
        }

        return direction;

    }


    public static void informAboutGamePanel(Painter p) {
        gamePanel = p;
    }

    public static void informAboutPlayer(Player pl) {
        player = pl;
    }

    public static void informAboutTimerForMovement(Timer t) {
        checkPlayerMovement = t;
    }

    /**
     * Update player position whenever timer issues actionPerformed.
     */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == checkPlayerMovement) {
            int[] pos = player.getPosition();
            int x = pos[0];
            int y = pos[1];

            if (right) {
                x += step;
            }

            if (left) {
                x -= step;
            }

            
            if (up) {
                y -= step;
            }

            
            if (down) {
                y += step;
            }

            String direction = getDirection(up, right, down, left);

            player.setPosition(x, y);
            player.setImage("player_" + direction);

            gamePanel.applyAnimation();
        }
    }

    /**
     * KeyListener method responsible for detecting pressed WSAD keys.
     */
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        
        if (key == 'w') {
            up = true;
        }

        if (key == 's') {
            down = true;
        }

        if (key == 'a') {
            left = true;
        }

        if (key == 'd') {
            right = true;
        }
    }

    public void keyTyped(KeyEvent e) {}

    /**
    * KeyListener method responsible for detecting released WSAD keys.
    */
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        
        if (key == 'w') {
            up = false;
        }

        if (key == 's') {
            down = false;
        }

        if (key == 'a') {
            left = false;
        }

        if (key == 'd') {
            right = false;
        }
    }    
}
