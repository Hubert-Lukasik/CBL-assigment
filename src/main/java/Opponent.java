package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private int xDir = SPEED;
    private int yDir = SPEED;

    private static Random rand = new Random();

    private void setTimer() {
        this. checkPlayerPositionTimer = new Timer(500 + rand.nextInt(1000), this);
        checkPlayerPositionTimer.start();
    }

    /**
     * Add new opponent to show it on the screen.
     */
    public static void addOpponent() {
        Opponent newOpponent = new Opponent();
        newOpponent.setImage("test_char2");
        newOpponent.setPosition(rand.nextInt(500), rand.nextInt(500));
        opponents.add(newOpponent);
        newOpponent.setTimer();
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
    * TODO: add going to player direction.
    * END TODO
    */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == checkPlayerPositionTimer) {
            int[] playerPosition = player.getPosition();            

            for (Opponent op : opponents) {
                int[] opPosition = op.getPosition();

                op.xDir = SPEED;
                op.yDir = SPEED;

                op.xDir = Math.min(Math.abs(xDir), Math.abs(opPosition[0] - playerPosition[0]));
                op.yDir = Math.min(Math.abs(yDir), Math.abs(opPosition[1] - playerPosition[1]));


                if (playerPosition[0] < opPosition[0]) {
                    op.xDir = -1 * Math.abs(op.xDir);
                } else if (playerPosition[0] > opPosition[0]) {
                    op.xDir = Math.abs(op.xDir);
                } 

                if (playerPosition[1] < opPosition[1]) {
                    op.yDir = -1 * Math.abs(op.yDir);
                } else if (playerPosition[1] > opPosition[1]) {
                    op.yDir = Math.abs(xDir);
                } 
            }

            for (Opponent op : opponents) {
                int[] opPosition = op.getPosition();
                op.setPosition(opPosition[0] + op.xDir, opPosition[1] + op.yDir);
            }
        
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
