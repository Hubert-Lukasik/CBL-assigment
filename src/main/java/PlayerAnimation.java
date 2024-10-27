package src.main.java;

import java.awt.event.*;
import javax.swing.*;

/**
 * Class is responsible for animating player character.
 */
public class PlayerAnimation implements ActionListener {
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

            if (player.isAttacking()) {
                step = Constants.getPlayerAttackStep();
            } else {
                step = Constants.getPlayerStep();
            }

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

            String direction = Entity.getDirection(up, right, down, left);

            //player is moving
            if (direction != "" && !player.isAttacking()) {
                player.setCurrentDirection(up, right, down, left);
                player.setImage("player_" + direction);
            }

            player.setPosition(x, y);
        }
    }

    /**
     * Makes player character go right.
     */
    public void goRight() {
        boolean[] collision = player.checkCollision();

        if (!collision[4]) {
            right = true;
            left = false;
        }
    }

    public void relaseRight() {
        right = false;
    }
    
    /**
     * Makes player character go left.
     */
    public void goLeft() {
        boolean[] collision = player.checkCollision();

        if (!collision[2]) {
            left = true;
            right = false;
        }
    }

    public void releaseLeft() {
        left = false;
    }

    /**
     * Makes player character go up.
     */
    public void goUp() {
        boolean[] collision = player.checkCollision();

        if (!collision[1]) {
            up = true;
            down = false;
        }
    }

    public void releaseUp() {
        up = false;
    }

    /**
     * Makes player character go down.
     */
    public void goDown() {
        boolean[] collision = player.checkCollision();

        if (!collision[3]) {
            down = true;
            up = false;
        }
    }

    public void releaseDown() {
        down = false;
    }

    /**
     * Makes player character use weapon.
     */
    public void useWeapon() {
        boolean[] curDir = player.getCurrentDirection(); 
        player.getWeapon().swingWeapon(curDir[0], curDir[1], curDir[2], curDir[3]);
    }
    
    /**
     * Constructor for PlayerAnimation class.
     * @param p - Player object
     */
    public PlayerAnimation(Player p) {
        player = p;
        up = false;
        right = false;
        down = false;
        left = false;
        checkPlayerMovement = new Timer(Constants.howOftenPlayerPositionIsUpdated(), this);
        checkPlayerMovement.start();
    }
}
