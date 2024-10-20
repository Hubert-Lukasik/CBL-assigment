package src.main.java;

import java.awt.event.*;
import javax.swing.*;

/**
 * Class is responsible for animating player character.
 */
public class PlayerAnimation implements KeyListener, ActionListener {
    private Painter gamePanel;
    private Player player;
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;
    private int step;
    private Timer checkPlayerMovement;

    /**
     * Determine geographic direction towards the player character is moving.
     * @param up - is character moving up
     * @param right - is character moving right
     * @param down - is character moving down
     * @param left - is character moiving left
     * @return - String containing letters corresponding to 
     *     the direction of the player character movement 
     *     (empty if direction is undetermined)
     */
    public String getDirection(boolean up, boolean right, boolean down, boolean left) {
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
    
    /**
     * Constructor for PlayerAnimation class.
     * @param p - Player object
     * @param g - Painter object
     */
    public PlayerAnimation(Player p, Painter g) {
        player = p;
        gamePanel = g;
        up = false;
        right = false;
        down = false;
        left = false;
        step = Constants.getPlayerStep();
        checkPlayerMovement = new Timer(Constants.howOftenPlayerPositionIsUpdated(), this);
        checkPlayerMovement.start();
    }
}
