package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class containing methods for opponents.
 */
public class Opponent extends Entity implements ActionListener {
    
    private static Player player;
    private static ArrayList<Opponent> opponents = new ArrayList<Opponent>();
    private static Painter gamePanel;

    private Timer checkPlayerPositionTimer;

    private void setTimer() {
        this. checkPlayerPositionTimer = new Timer(1000, this);
        checkPlayerPositionTimer.start();
    }

    /**
     * Add new opponent to show it on the screen.
     */
    public static void addOpponent() {
        Opponent newOpponent = new Opponent();
        newOpponent.setImage("test_char2");
        newOpponent.setPosition(300, 300);
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
                op.setPosition(playerPosition[0], playerPosition[1]);
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
