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
     * Determine geographic direction towards the player character is moving.
     * @param up - is character moving up
     * @param right - is character moving right
     * @param down - is character moving down
     * @param left - is character moiving left
     * @return - String containing letters corresponding the direction of the player character movement (empty if direction is undetermined)
     */
    public static String getDirection(boolean up, boolean right, boolean down, boolean left) {
        String direction = "";

        //exclude situation when two opposing keys are pressed

        if (up && !down) {
            direction += 'n';
        } 
        
        if (down && !up) {
            direction += 's';
        }

        if (left && !right) {
            direction += 'w';
        }

        if (right && !left) {
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

            //player is moving
            if (direction != "") {
                player.setImage("player_" + direction);
            }

            player.setPosition(x, y);

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
