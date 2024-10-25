package src.main.java;

import java.awt.*;
import java.awt.event.ActionListener;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Class containing methods for opponents.
 */
public class Opponent extends Entity implements Runnable {
    
    private static Player player;
    private static ArrayList<Entity> opponents = new ArrayList<Entity>();
    private static Painter gamePanel;
    private static final int SPEED = 5;
    private static Random rand = new Random();


    /**
     * Add new opponent to show it on the screen.
     */
    public static void addOpponent() {
        Opponent newOpponent = new Opponent();
        Thread opponentThread = new Thread(newOpponent);
        newOpponent.setImage("test_char2");
        newOpponent.setPosition(rand.nextInt(100), rand.nextInt(500));
        newOpponent.addCollision();
        opponents.add(newOpponent);
        opponentThread.start();
    }

    public static void killOpponents() {
        kill(opponents);
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

    @Override
    public void run() {
        while (opponents.contains(this)) {
            boolean up = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;
            
            int[] playerPosition = player.getPosition();    

            Opponent op = this;
            // for (Opponent op : opponents) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {

            }

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
            gamePanel.update();
        }
        
    }

    /**
     * Draw an opponent.
     */
    public static void draw(Graphics g, Painter p) {
        for (int i = 0; i < opponents.size(); ++i) {
            int[] position = opponents.get(i).getPosition();
            g.drawImage(opponents.get(i).getImage(), position[0], position[1], p);
            // Rectangle[] r = opponents.get(i).getHitbox();
            // for (int j = 0; j < 5; j++) {
            //     g.drawRect(r[j].x, r[j].y, r[j].width, r[j].height);
            // }
        }
    }
}
