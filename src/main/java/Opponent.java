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
    private static ArrayList<Entity> opponents = new ArrayList<Entity>();
    private static ArrayList<Entity> endingOpponents = new ArrayList<Entity>();
    private static Painter gamePanel;

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

    /**
     * Adding opponent to ending sequance.
     */
    public static void endingAddOpponent() {
        Opponent newOpponent = new Opponent();
        newOpponent.setImage("test_char2");
        newOpponent.setPosition(Constants.getTileWidht(), 
            Constants.getTileHeight() 
                + rand.nextInt(Constants.getMapHeight() - 3 * Constants.getTileHeight()));
        endingOpponents.add(newOpponent);
    }

    /**
     * Kills all opponents.
     */
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

    /**
     * Find the nearest opponent to the specified coordinates.
     * @param x - x-coordinate of the specified position
     * @param y x-coordinate of the specified position
     * @return - array containg two ints of the closest enemy or [-1, -1] is no enemy was found
     */
    public static int[] findNearestOpponent(int x, int y) {
        int[] nearestOpponent = new int[2];
        nearestOpponent[0] = -1;
        nearestOpponent[0] = -1;

        int smallestDistance = Integer.MAX_VALUE;
        int actDistance;

        for (Entity o : opponents) {
            int[] opponentPos = o.getPosition();
            actDistance = (opponentPos[0] - x) * (opponentPos[0] - x) 
                + (opponentPos[1] - y) * (opponentPos[1] - y);
            if (actDistance < smallestDistance) {
                nearestOpponent = opponentPos;
                smallestDistance = actDistance;
            }
        }

        return nearestOpponent;
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

            if (this.isAttacking()) {
                return;
            }
            int[] playerPosition = player.getPosition();    

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

            String direction = Entity.getDirection(up, right, down, left);

            //opponent is moving
            if (direction != "") {
                this.setCurrentDirection(up, right, down, left);
                this.setImage("opponent_" + direction);
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
    public static void draw(Graphics g, JPanel p) {
        for (int i = 0; i < opponents.size(); ++i) {
            int[] position = opponents.get(i).getPosition();
            g.drawImage(opponents.get(i).getImage(), position[0], position[1], p);
            // Rectangle[] r = opponents.get(i).getHitbox();
            // for (int j = 0; j < 5; j++) {
            //     g.drawRect(r[j].x, r[j].y, r[j].width, r[j].height);
            // }
        }
    }

    /**
     * Draw opponents in the ending section.
     */
    public static void drawEndingOpponents(Graphics g, JPanel p) {
        for (Entity o : endingOpponents) {
            o.setPosition(o.getPosition()[0] + 4 * Constants.getOpponentStep(), o.getPosition()[1]);
            g.drawImage(o.getImage(), o.getPosition()[0], o.getPosition()[1], p);
        }
    }
}
