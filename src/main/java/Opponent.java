package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
    private Timer checkTargetPositionTimer;
    private static Random rand = new Random();
    private boolean updateOpponent;
    private static TurretManager turretManager;

    private void setTimer() {
        this. checkTargetPositionTimer = new Timer(
            Constants.getMinimumDelayForCheckingPlayerPosition() + rand.nextInt(50), this);
        checkTargetPositionTimer.start();
    }

    /**
     * Add new opponent to show it on the screen.
     */
    public static void addOpponent() {
        Opponent newOpponent = new Opponent();
        newOpponent.setImage("test_char2");
        int[] randPos = getRandomPossition();
        newOpponent.setPosition(randPos[0], randPos[1]);
        newOpponent.addCollision();
        newOpponent.setHealthPoints(100);
        newOpponent.getWeapon().setDamage(25);
        opponents.add(newOpponent);
        newOpponent.setTimer();
    }


    
    private static int[] getRandomPossition() {
        int[] pos = new int[2];
        int i = rand.nextInt(1, 5);
        switch (i) {
            case 1 -> {
                pos[0] = 40;
                pos[1] = rand.nextInt(40, Constants.getMapHeight() - 40);
            }
            case 2 -> {
                pos[0] = Constants.getMapWidth() + 40;
                pos[1] = rand.nextInt(40, Constants.getMapHeight() - 40);
            }
            case 3 -> {
                pos[0] = rand.nextInt(40, Constants.getMapWidth() - 40);
                pos[1] = 40;
            }
            case 4 -> {
                pos[0] = rand.nextInt(40, Constants.getMapWidth() - 40);
                pos[1] = Constants.getMapHeight() + 40;
            }
            default -> {
                pos[0] = 40;
                pos[1] = 40;  
            }
        }

        return pos;
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
        for (Entity o: opponents) {
            o.setHealthPoints(0);
        }
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

    public static void informAboutTurretManager(TurretManager t) {
        turretManager = t;
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

    private int attackCycle = 0;

    /**
     * Calculate "distance" between two points. 
     * @param coordinates1 - first coordinates
     * @param coordinates2 - second coordinates
     * @return - sum of squared differences
     */
    private long calculateDistance(int[] coordinates1, int[] coordinates2) {
        long distance = 0;
        long x1 = (long) coordinates1[0];
        long y1 = (long) coordinates1[1];
        long x2 = (long) coordinates2[0];
        long y2 = (long) coordinates2[1];
        distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return distance;
    }

    /**
     * Listener for updating position of the opponents.
     */
    public void actionPerformed(ActionEvent t) {

        if (isDead()) {
            opponents.remove(this);
            kill(this);
            return;
        }

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        
        if (t.getSource() == checkTargetPositionTimer) {

            if (this.isDead()) {
                kill(this);
                opponents.remove(this);
            }

            if (this.isAttacking()) {
                return;
            }

            int[] nearestTurret = turretManager.nearestTurret(
                this.getPosition()[0], this.getPosition()[1]);
            int[] playerPos = player.getPosition();
            int[] targetPosition = new int[2];

            if  (nearestTurret[0] != -1 && calculateDistance(nearestTurret, this.getPosition()) 
                < calculateDistance(playerPos, this.getPosition())) {
                targetPosition = nearestTurret;
            } else {
                targetPosition = playerPos;
            }

            Opponent op = this;
                
            int[] opPosition = op.getPosition();
            boolean[] collision = op.checkCollision();

            int x = opPosition[0];
            int y = opPosition[1];

            int step = Constants.getOpponentStep();

            if (Math.abs(x - targetPosition[0]) > Constants.getPlayerStep()) {
                if (x < targetPosition[0]) {
                    right = true;
                } else if (x > targetPosition[0]) {
                    left = true;
                }
            }

            if (Math.abs(y - targetPosition[1]) > Constants.getPlayerStep()) {
                if (y < targetPosition[1]) {
                    down = true;
                } else if (y > targetPosition[1]) {
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
            if (direction != "" && !this.isAttacking()) {
                this.setCurrentDirection(up, right, down, left);
                this.setImage("opponent/opponent_" + direction);
            } 

            if (updateOpponent) {
                boolean[] dir = this.getCurrentDirection();
                String curDir = Entity.getDirection(dir[0], dir[1], dir[2], dir[3]);
                this.setImage("opponent/opponent_" + curDir);
                updateOpponent = false;
            }
            
            if (this.getTookDamage()) {
                boolean[] dir = this.getCurrentDirection();
                String curDir = Entity.getDirection(dir[0], dir[1], dir[2], dir[3]);
                this.setImage("opponent_damage/opponent_" + curDir);
                this.setTookDamage(false);
                updateOpponent = true;
            } 
            //}
            
            up = false;
            down = false;
            left = false;            
            right = false;


            op.setPosition(x, y);

            boolean[] dir = getCurrentDirection();

            if (hittableEntity() && attackCycle == 4) {
                this.getWeapon().swingWeapon(dir[0], dir[1], dir[2], dir[3]);
                attackCycle = 0;
            } else if (hittableEntity()) {
                attackCycle++;
            }

            gamePanel.update();
        }
    }

    private boolean hittableEntity() {
        boolean[] collision = this.checkCollision();
        int[] pos = this.getPosition();
        int x = pos[0];
        int y = pos[1];

        for (int i = 1; i <= 4; i++) {
            if (collision[i]) {
                int width = this.getImage().getWidth();
                int height = this.getImage().getHeight();
                Rectangle hitCheck = new Rectangle(x - 20, y - 20, width + 40, height + 40);
                for (Entity e : checkHit(hitCheck)) {
                    if (!opponents.contains(e)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<Entity> getOpponents() {
        return opponents;
    }

    /**
     * Draw an opponent.
     */
    public static void draw(Graphics g, JPanel p) {
        for (int i = 0; i < opponents.size(); ++i) {
            int[] position = opponents.get(i).getPosition();
            g.drawImage(opponents.get(i).getImage(), position[0], position[1], p);
            opponents.get(i).getWeapon().draw(g, p);
        }
    }

    /**
     * Draw opponents in the ending section.
     */
    public static void drawEndingOpponents(Graphics g, JPanel p) {
        for (Entity o : endingOpponents) {
            o.setPosition(o.getPosition()[0] + Constants.getOpponentStep(), o.getPosition()[1]);
            g.drawImage(o.getImage(), o.getPosition()[0], o.getPosition()[1], p);
        }
    }
}
