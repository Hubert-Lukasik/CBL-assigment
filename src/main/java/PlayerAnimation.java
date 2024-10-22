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
     * Update player position whenever timer issues actionPerformed.
     */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == checkPlayerMovement) {
            int[] pos = player.getPosition();
            boolean[] collision = player.checkCollision();
            int x = pos[0];
            int y = pos[1];

            
            if (right && !collision[2]) {
                x += step;
            }

            if (left && !collision[4]) {
                x -= step;
            }

            
            if (up && !collision[1]) {
                y -= step;
            }

            
            if (down && !collision[3]) {
                y += step;
            }

            String direction = Entity.getDirection(up, right, down, left);

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
