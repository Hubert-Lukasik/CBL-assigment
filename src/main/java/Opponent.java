package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Class containing methods for opponents.
 */
public class Opponent extends Entity implements ActionListener {
    
    private static Player player;
    private static ArrayList<Opponent> opponents = new ArrayList<Opponent>();
    private static Painter gamePanel;
    private static final int SPEED = 5;

    private Timer checkPlayerPositionTimer;
    private static Random rand = new Random();

    private void setTimer() {
        this. checkPlayerPositionTimer = new Timer(20 + rand.nextInt(50), this);
        checkPlayerPositionTimer.start();
    }

    /**
     * Add new opponent to show it on the screen.
     */
    public static void addOpponent() {
        Opponent newOpponent = new Opponent();
        newOpponent.setImage("test_char2");
        newOpponent.setPosition(rand.nextInt(100), rand.nextInt(500));
        newOpponent.addCollision();
        opponents.add(newOpponent);
        newOpponent.setTimer();
    }

    public static void clearOpponentsArray() {
        opponents.clear();
    }

    public static int howManyOpponents() {
        return opponents.size();
    }

    public static void informAboutPlayer(Player p) {
        player = p;
    }

    public static void informAboutGamePanel(Painter p) {
        gamePanel = p;
    }

    /**
     * Listener for updating position of the opponents.
     */
    public void actionPerformed(ActionEvent t) {

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        
        if (t.getSource() == checkPlayerPositionTimer) {
            int[] playerPosition = player.getPosition();    
            int[] targetPosition = new int[2];        

            Opponent op = this;
            // for (Opponent op : opponents) {
                
            int[] opPosition = op.getPosition();
            boolean[] collision = op.checkCollision();

            int x = opPosition[0];
            int y = opPosition[1];

            int step = Constants.getOpponentStep();

            if (Math.abs(x - playerPosition[0]) > Constants.getPlayerStep()) {
                if (x < playerPosition[0]) {
                    right = true;
                } else if (x > playerPosition[0]) {
                    left = true;
                }
            }

            if (Math.abs(y - playerPosition[1]) > Constants.getPlayerStep()) {
                if (y < playerPosition[1]) {
                    down = true;
                } else if (y > playerPosition[1]) {
                    up = true;
                } 
            }

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

            //}

            up = false;
            down = false;
            left = false;            
            right = false;


            op.setPosition(x, y);

            gamePanel.applyAnimation();
        }
    }

    /**
     * Draw an opponent.
     */
    public static void draw(Graphics g, Painter p) {
        for (int i = 0; i < opponents.size(); ++i) {
            int[] position = opponents.get(i).getPosition();
            g.drawImage(opponents.get(i).getImage(), position[0], position[1], p);
        }
    }
}
