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

    private boolean updatePlayer;


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
            boolean[] collision = player.checkCollision();

            
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
            if (direction != "" && !player.isAttacking()) {
                player.setCurrentDirection(up, right, down, left);
                player.setImage("player/player_" + direction);
            } 

            if (updatePlayer) {
                boolean[] dir = player.getCurrentDirection();
                String curDir = Entity.getDirection(dir[0], dir[1], dir[2], dir[3]);
                player.setImage("player/player_" + curDir);
                updatePlayer = false;
            }
            
            if (player.getTookDamage()) {
                boolean[] dir = player.getCurrentDirection();
                String curDir = Entity.getDirection(dir[0], dir[1], dir[2], dir[3]);
                player.setImage("player_damage/player_" + curDir);
                player.setTookDamage(false);
                updatePlayer = true;
            } 


            player.setPosition(x, y);
        }
    }

    /**
     * Makes player character go right.
     */
    public void goRight() {
            right = true;
            left = false;
    }

    public void relaseRight() {
        right = false;
    }
    
    /**
     * Makes player character go left.
     */
    public void goLeft() {
        left = true;
        right = false;
    }

    public void releaseLeft() {
        left = false;
    }

    /**
     * Makes player character go up.
     */
    public void goUp() {
        up = true;
        down = false;
    }

    public void releaseUp() {
        up = false;
    }

    /**
     * Makes player character go down.
     */
    public void goDown() {
        down = true;
        up = false;
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
